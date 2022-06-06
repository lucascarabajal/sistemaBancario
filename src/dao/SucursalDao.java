package dao;

import config.PropertiesCache;
import models.Banco;
import models.Cliente;
import models.Sucursal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SucursalDao implements ISucursalDao{

    private final List<Sucursal> sucursales;

    public SucursalDao(){
        this.sucursales = new ArrayList<>();
    }

    @Override
    public void addSucursal(Sucursal sucursal, Integer idBanco) {

        Connection connection;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            //CONNECTION
            connection = DriverManager.getConnection(PropertiesCache.getInstance().getProperty("db.url"),
                    PropertiesCache.getInstance().getProperty("db.username"),
                    PropertiesCache.getInstance().getProperty("db.password"));

            //QUERY
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO sucursal (direccion, id_banco) VALUES (?,?)");

            preparedStatement.setString(1,sucursal.getDireccion());
            preparedStatement.setInt(2,idBanco);

            preparedStatement.executeUpdate();

            connection.close();
        }catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    @Override
    public Sucursal findSucursalById(Integer id) {
        Connection connection;
        Sucursal sucursal;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            //CONNECTION
            connection = DriverManager.getConnection(PropertiesCache.getInstance().getProperty("db.url"),
                    PropertiesCache.getInstance().getProperty("db.username"),
                    PropertiesCache.getInstance().getProperty("db.password"));

            //QUERY
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM sucursal WHERE id_sucursal = ?");
            preparedStatement.setInt(1,id);

            ResultSet rs = preparedStatement.executeQuery();


            while(rs.next()){
                sucursal = new Sucursal();
                sucursal.setId(rs.getInt(1));
                sucursal.setDireccion(rs.getString(2));
                return sucursal;
            }

            connection.close();

        }catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return null;
    }

    @Override
    public List<Sucursal> listSucursales() {
        Connection connection;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            //CONNECTION
            connection = DriverManager.getConnection(PropertiesCache.getInstance().getProperty("db.url"),
                    PropertiesCache.getInstance().getProperty("db.username"),
                    PropertiesCache.getInstance().getProperty("db.password"));

            Statement statement = connection.createStatement();

            //QUERY
            ResultSet resultSet = statement.executeQuery("SELECT id_sucursal, direccion FROM sucursal");

            Sucursal sucursal;

            while(resultSet.next()){
                sucursal = new Sucursal();
                sucursal.setId(resultSet.getInt(1));
                sucursal.setDireccion(resultSet.getString(2));

                sucursales.add(sucursal);
            }

            connection.close();
        }catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return sucursales;
    }

    @Override
    public void deleteSucursal(Sucursal deleteSucural, Sucursal newSucursal) {
        Connection connection;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            //CONNECTION
            connection = DriverManager.getConnection(PropertiesCache.getInstance().getProperty("db.url"),
                    PropertiesCache.getInstance().getProperty("db.username"),
                    PropertiesCache.getInstance().getProperty("db.password"));

            //QUERY
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE cliente set id_sucursal = ? WHERE id_sucursal = ?");

            preparedStatement.setInt(1,newSucursal.getId());
            preparedStatement.setInt(2,deleteSucural.getId());

            preparedStatement.executeUpdate();


            //QUERY DELETE
            PreparedStatement preparedStatement1 = connection.prepareStatement("DELETE FROM sucursal WHERE id_sucursal = ?");

            preparedStatement1.setInt(1,deleteSucural.getId());

            preparedStatement1.executeUpdate();

            connection.close();
        }catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
}
