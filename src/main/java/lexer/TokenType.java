package lexer;

/**
 *  词法分析 —— 符号类型
 */
public enum TokenType {
    //关键字
    KEYWORD,
    //变量
    VARIABLE,
    //操作符
    OPERATOR,
    //括号
    BRACKET,
    //字符串
    STRING,
    //整形
    INTEGER,
    //浮点数
    FLOAT,
    //布尔
    BOOLEAN
}
