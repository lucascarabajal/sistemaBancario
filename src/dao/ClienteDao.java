package dao;

import config.PropertiesCache;
import models.Cliente;
import models.Cuenta;
import models.Sucursal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao implements IClienteDao{

    private final List<Cliente> clientes;

    public ClienteDao(){
        this.clientes = new ArrayList<>();
    }

    @Override
    public void addCliente(Cliente cliente) {
        Connection connection;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            //CONNECTION
            connection = DriverManager.getConnection(PropertiesCache.getInstance().getProperty("db.url"),
                    PropertiesCache.getInstance().getProperty("db.username"),
                    PropertiesCache.getInstance().getProperty("db.password"));

            //QUERY
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO cliente (id_sucursal,nombre,apellido,dni,fecha_nac,email,num_telefono,fecha_alta) VALUES (?,?,?,?,?,?,?,?)");

            preparedStatement.setInt(1,cliente.getSucursal().getId());
            preparedStatement.setString(2,cliente.getNombre());
            preparedStatement.setString(3,cliente.getApellido());
            preparedStatement.setString(4,cliente.getDni());
            preparedStatement.setDate(5,cliente.getFechaNac());
            preparedStatement.setString(6,cliente.getEmail());
            preparedStatement.setString(7,cliente.getNumTel());
            preparedStatement.setDate(8,cliente.getFechaAlta());

            preparedStatement.executeUpdate();

            connection.close();
        }catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    @Override
    public Cliente findByClienteByDni(String dni) {

        Connection connection;
        Cliente cliente;
        ISucursalDao sucursalDao = new SucursalDao();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            //CONNECTION
            connection = DriverManager.getConnection(PropertiesCache.getInstance().getProperty("db.url"),
                    PropertiesCache.getInstance().getProperty("db.username"),
                    PropertiesCache.getInstance().getProperty("db.password"));

            //QUERY
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM cliente WHERE dni LIKE ?");
            preparedStatement.setString(1,dni);

            ResultSet rs = preparedStatement.executeQuery();


            while (rs.next()){
                cliente = new Cliente();
                cliente.setId(rs.getInt(1));
                cliente.setSucursal(sucursalDao.findSucursalById(rs.getInt(2)));
                cliente.setNombre(rs.getString(3));
                cliente.setApellido(rs.getString(4));
                cliente.setDni(rs.getString(5));
                cliente.setFechaNac(rs.getDate(6));
                cliente.setEmail(rs.getString(7));
                cliente.setNumTel(rs.getString(8));
                cliente.setFechaAlta(rs.getDate(9));

                return cliente;
            }

            connection.close();

        }catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Cliente findClienteById(Integer idCliente) {

        Connection connection;
        Cliente cliente;
        ISucursalDao sucursalDao = new SucursalDao();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            //CONNECTION
            connection = DriverManager.getConnection(PropertiesCache.getInstance().getProperty("db.url"),
                    PropertiesCache.getInstance().getProperty("db.username"),
                    PropertiesCache.getInstance().getProperty("db.password"));

            //QUERY
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM cliente WHERE id_cliente = ?");
            preparedStatement.setInt(1,idCliente);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                cliente = new Cliente();
                cliente.setId(rs.getInt(1));
                cliente.setSucursal(sucursalDao.findSucursalById(rs.getInt(2)));
                cliente.setNombre(rs.getString(3));
                cliente.setApellido(rs.getString(4));
                cliente.setDni(rs.getString(5));
                cliente.setFechaNac(rs.getDate(6));
                cliente.setEmail(rs.getString(7));
                cliente.setNumTel(rs.getString(8));
                cliente.setFechaAlta(rs.getDate(9));
                return cliente;
            }

            connection.close();

        }catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return null;
    }

    @Override
    public List<Cliente> listClientes() {
        Connection connection;
        ISucursalDao sucursalDao = new SucursalDao();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            //CONNECTION
            connection = DriverManager.getConnection(PropertiesCache.getInstance().getProperty("db.url"),
                    PropertiesCache.getInstance().getProperty("db.username"),
                    PropertiesCache.getInstance().getProperty("db.password"));

            Statement statement = connection.createStatement();

            //QUERY
            ResultSet resultSet = statement.executeQuery("SELECT s.id_sucursal, cl.apellido, cl.nombre,cl.dni FROM cliente as cl INNER JOIN sucursal as s on (cl.id_sucursal=s.id_sucursal) ORDER BY id_sucursal");

            Cliente cliente;

            while(resultSet.next()){
                cliente = new Cliente();
                cliente.setSucursal(sucursalDao.findSucursalById(resultSet.getInt(1)));;
                cliente.setApellido(resultSet.getString(2));
                cliente.setNombre(resultSet.getString(3));
                cliente.setDni(resultSet.getString(4));

                clientes.add(cliente);
            }

            connection.close();
        }catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return clientes;
    }

    @Override
    public List<Cliente> listClientesBySucursal(Sucursal sucursal) {
        Connection connection;
        ISucursalDao sucursalDao = new SucursalDao();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            //CONNECTION
            connection = DriverManager.getConnection(PropertiesCache.getInstance().getProperty("db.url"),
                    PropertiesCache.getInstance().getProperty("db.username"),
                    PropertiesCache.getInstance().getProperty("db.password"));



            //QUERY
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT s.id_sucursal, cl.apellido, cl.nombre,cl.dni FROM cliente as cl INNER JOIN sucursal as s on (cl.id_sucursal=s.id_sucursal) SELECT s.id_sucursal = ?");
            preparedStatement.setInt(1,sucursal.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            Cliente cliente;

            while(resultSet.next()){
                cliente = new Cliente();
                cliente.setSucursal(sucursalDao.findSucursalById(resultSet.getInt(1)));;
                cliente.setApellido(resultSet.getString(2));
                cliente.setNombre(resultSet.getString(3));
                cliente.setDni(resultSet.getString(4));

                clientes.add(cliente);
            }

            connection.close();
        }catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return clientes;
    }
}
