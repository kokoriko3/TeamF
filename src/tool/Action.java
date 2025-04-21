package tool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 抽象クラス
// 完成していないクラス(中身がないクラス)
//  ⇒使用する場合は、「継承」が必要
public abstract class Action {
	// メソッド名：execute
	public abstract void execute(
			HttpServletRequest req, HttpServletResponse res
		)throws Exception;
}
