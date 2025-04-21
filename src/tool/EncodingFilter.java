package tool;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

// アノテーション：URL指定 *:全て(共通部分のため)
@WebFilter(urlPatterns={"/*"})
// Filter(インターフェース：抽象クラスのようなもの)
// を継承⇒ implements
public class EncodingFilter implements Filter {

	public void doFilter(
			ServletRequest request, ServletResponse response, FilterChain chain
		) throws IOException, ServletException{

		request.setCharacterEncoding("UTF-8");

		// ↑サーブレットやJSPを呼び出す前の処理
		chain.doFilter(request,response);
		// ↓サーブレットやJSPを呼びだす後の処理

	}
	public void init(FilterConfig filterconfig){}
	public void destroy()  {}
}
