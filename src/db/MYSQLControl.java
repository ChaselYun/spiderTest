package db;



import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import model.FilmModel;

public class MYSQLControl {
	//根据自己的数据库地址修改
	static DataSource ds = MyDataSource.getDataSource("jdbc:mysql://127.0.0.1:3306/t");
	 static QueryRunner qr = new QueryRunner(ds);
    //第一类方法
    public static void executeUpdate(String sql){
        try {
            qr.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //第二类数据库操作方法
    public static void executeInsert(List<FilmModel> filmdata) throws SQLException {
        /*
         * 定义一个Object数组，行列
         * 3表示列数，根据自己的数据定义这里面的数字
         * params[i][0]等是对数组赋值，这里用到集合的get方法
         * 
         */
        Object[][] params = new Object[filmdata.size()][3];
        for ( int i=0; i<params.length; i++ ){
            params[i][0] = filmdata.get(i).getFilmName();
            params[i][1] = filmdata.get(i).getSchedule();
            params[i][2] = filmdata.get(i).getBoxOffice();
        }
        qr.batch("insert into film (filmName, schedule, boxOffice)"
                + "values (?,?,?)", params);
        System.out.println("执行数据库完毕！"+"成功插入数据："+filmdata.size()+"条");

    }
}
