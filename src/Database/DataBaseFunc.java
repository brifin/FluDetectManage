package Database;

//定义数据库的操作
interface DataBaseFunc {

//    必须实现数据库录入
    String register(Object user);

//    必须实现数据库修改
    String Revise(Object user);

//    必须实现数据库关闭
    void close();

}
