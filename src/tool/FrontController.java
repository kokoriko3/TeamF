// フロントコントローラー
// urlを指定する指示役

// ①全ての行き先をいったん引き受ける
// ②受け取ったURLをパッケージ名.クラス名に変換
// ③各処理を呼びだし、渡すURLを受け取る
// ④受け取ったURLへフォワード

package tool;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// ○○.actionはすべてここを通る
// jspに記述するurlは○○.aciton
@WebServlet(urlPatterns={"*.action"})
public class FrontController extends HttpServlet{

	@Override
	protected void doGet(
			HttpServletRequest request,HttpServletResponse response
		)throws ServletException, IOException{
		try{
			// パスを取得
			String path=request.getServletPath().substring(1);
			// ファイル名を取得しクラス名に変換
			String name=path.replace(".a", "A").replace('/', '.');

			System.out.println("★servlet path - >" + request.getServletPath());
			System.out.println("★class name ->"+ name);

			// アクションクラスのインスタンスを返却
			Action action=(Action)Class.forName(name).
					getDeclaredConstructor().newInstance();
			// 遷移先urlを取得
			action.execute(request, response);

//			String url = action.execute(request, response);
//
//
//			request.getRequestDispatcher(url).
//				forward(request,response);
		}catch (Exception e){
			request.getRequestDispatcher("/error.jsp").forward(request, response);
			e.printStackTrace();
		}
	}

	public void doPost(
			HttpServletRequest request,HttpServletResponse response
			)throws ServletException, IOException{
			doGet(request, response);
	}
}
