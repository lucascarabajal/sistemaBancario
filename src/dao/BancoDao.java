package dao;

import config.PropertiesCache;
import models.Banco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BancoDao implements IBancoDao{
    private final List<Banco> bancos;

    public BancoDao(){
        this.bancos = new ArrayList<>();
    }

    @Override
    public void addBanco(Banco banco) {

        Connection connection;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            //CONNECTION
            connection = DriverManager.getConnection(PropertiesCache.getInstance().getProperty("db.url"),
                    PropertiesCache.getInstance().getProperty("db.username"),
                    PropertiesCache.getInstance().getProperty("db.password"));

            //QUERY
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO banco (nombre) VALUES (?)",PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1,banco.getNombre());

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            while (rs.next()){
                banco.setId(rs.getInt(1));
            }

            connection.close();
        }catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    @Override
    public Banco findBancoById(Integer idBanco) {
        Connection connection;
        Banco banco;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            //CONNECTION
            connection = DriverManager.getConnection(PropertiesCache.getInstance().getProperty("db.url"),
                    PropertiesCache.getInstance().getProperty("db.username"),
                    PropertiesCache.getInstance().getProperty("db.password"));

            //QUERY
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM banco WHERE id_banco = ?");
            preparedStatement.setInt(1,idBanco);

            ResultSet rs = preparedStatement.executeQuery();


            while (rs.next()){
                banco = new Banco();
                banco.setId(rs.getInt(1));
                banco.setNombre(rs.getString(2));
                return banco;
            }

            connection.close();

        }catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return null;
    }
}
