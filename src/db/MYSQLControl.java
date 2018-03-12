package db;



import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import model.FilmModel;

public class MYSQLControl {
	//�����Լ������ݿ��ַ�޸�
	static DataSource ds = MyDataSource.getDataSource("jdbc:mysql://127.0.0.1:3306/t");
	 static QueryRunner qr = new QueryRunner(ds);
    //��һ�෽��
    public static void executeUpdate(String sql){
        try {
            qr.update(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //�ڶ������ݿ��������
    public static void executeInsert(List<FilmModel> filmdata) throws SQLException {
        /*
         * ����һ��Object���飬����
         * 3��ʾ�����������Լ������ݶ��������������
         * params[i][0]���Ƕ����鸳ֵ�������õ����ϵ�get����
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
        System.out.println("ִ�����ݿ���ϣ�"+"�ɹ��������ݣ�"+filmdata.size()+"��");

    }
}
