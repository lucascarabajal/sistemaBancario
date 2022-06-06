package dao;

import config.PropertiesCache;
import models.Cuenta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CuentaDao implements ICuentaDao{

    private List<Cuenta> cuentas;

    public CuentaDao(){
        this.cuentas = new ArrayList<>();
    }

    @Override
    public void addCuenta(Cuenta cuenta) {

        Connection connection;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            //CONNECTION
            connection = DriverManager.getConnection(PropertiesCache.getInstance().getProperty("db.url"),
                    PropertiesCache.getInstance().getProperty("db.username"),
                    PropertiesCache.getInstance().getProperty("db.password"));

            //QUERY Insertar cuenta
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("INSERT INTO cuenta(id_banco,id_cliente, cbu,saldo,tipo_moneda,tipo) VALUES (?,?,?,?,?,?) ");

            preparedStatement.setInt(1,cuenta.getBanco().getId());
            preparedStatement.setInt(2,cuenta.getCliente().getId());
            preparedStatement.setLong(3,cuenta.getCbu());
            preparedStatement.setDouble(4,cuenta.getSaldo());
            preparedStatement.setInt(5,cuenta.getTipoMoneda());
            preparedStatement.setInt(6,cuenta.getTipoCuenta());

            preparedStatement.executeUpdate();
            connection.close();

        }catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    @Override
    public List<Cuenta> findCuentasByDni(String dni) {
        Connection connection;
        IBancoDao bancoDao = new BancoDao();
        IClienteDao clienteDao = new ClienteDao();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            //CONNECTION
            connection = DriverManager.getConnection(PropertiesCache.getInstance().getProperty("db.url"),
                    PropertiesCache.getInstance().getProperty("db.username"),
                    PropertiesCache.getInstance().getProperty("db.password"));

            Statement statement = connection.createStatement();

            //QUERY
            ResultSet resultSet = statement.executeQuery("SELECT c.* FROM cuenta as c INNER JOIN cliente as cl on (c.id_cliente=cl.id_cliente) WHERE cl.dni = "+dni);

            Cuenta cuenta;

            while(resultSet.next()){
                cuenta = new Cuenta();
                cuenta.setId(resultSet.getInt(1));
                cuenta.setBanco(bancoDao.findBancoById(resultSet.getInt(2)));
                cuenta.setCliente(clienteDao.findClienteById(resultSet.getInt(3)));
                cuenta.setCbu(resultSet.getLong(4));
                cuenta.setSaldo(resultSet.getDouble(5));
                cuenta.setTipoMoneda(resultSet.getInt(6));
                cuenta.setTipoCuenta(resultSet.getInt(7));

                cuentas.add(cuenta);
            }

            connection.close();
        }catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return cuentas;
    }

    @Override
    public Cuenta findCuentaById(Integer idCuenta) {
        Connection connection;
        Cuenta cuenta;
        IBancoDao bancoDao = new BancoDao();
        IClienteDao clienteDao = new ClienteDao();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            //CONNECTION
            connection = DriverManager.getConnection(PropertiesCache.getInstance().getProperty("db.url"),
                    PropertiesCache.getInstance().getProperty("db.username"),
                    PropertiesCache.getInstance().getProperty("db.password"));

            //QUERY
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM cuenta WHERE id_cuenta LIKE ?");
            preparedStatement.setInt(1,idCuenta);

            ResultSet rs = preparedStatement.executeQuery();


            while (rs.next()){
                cuenta = new Cuenta();
                cuenta.setId(rs.getInt(1));
                cuenta.setBanco(bancoDao.findBancoById(rs.getInt(2)));
                cuenta.setCliente(clienteDao.findClienteById(rs.getInt(3)));
                cuenta.setCbu(rs.getLong(4));
                cuenta.setSaldo(rs.getDouble(5));
                cuenta.setTipoMoneda(rs.getInt(6));
                cuenta.setTipoCuenta(rs.getInt(7));
                return cuenta;
            }

            connection.close();

        }catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Cuenta findCuentaByCbu(Long cbu) {
        Connection connection;
        Cuenta cuenta;
        IBancoDao bancoDao = new BancoDao();
        IClienteDao clienteDao = new ClienteDao();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            //CONNECTION
            connection = DriverManager.getConnection(PropertiesCache.getInstance().getProperty("db.url"),
                    PropertiesCache.getInstance().getProperty("db.username"),
                    PropertiesCache.getInstance().getProperty("db.password"));

            //QUERY
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM cuenta WHERE cbu = ?");
            preparedStatement.setLong(1,cbu);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                cuenta = new Cuenta();
                cuenta.setId(rs.getInt(1));
                cuenta.setBanco(bancoDao.findBancoById(rs.getInt(2)));
                cuenta.setCliente(clienteDao.findClienteById(rs.getInt(3)));
                cuenta.setCbu(rs.getLong(4));
                cuenta.setSaldo(rs.getDouble(5));
                cuenta.setTipoMoneda(rs.getInt(6));
                cuenta.setTipoCuenta(rs.getInt(7));
                return cuenta;
            }

            connection.close();
        }catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        return null;
    }

    @Override
    public void updateSaldo(Cuenta cuenta) {
        Connection connection;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            //CONNECTION
            connection = DriverManager.getConnection(PropertiesCache.getInstance().getProperty("db.url"),
                    PropertiesCache.getInstance().getProperty("db.username"),
                    PropertiesCache.getInstance().getProperty("db.password"));

            //QUERY Insertar cuenta
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("UPDATE cuenta SET saldo = ? WHERE id_cuenta = ?");

            preparedStatement.setDouble(1,cuenta.getSaldo());
            preparedStatement.setInt(2,cuenta.getId());

            preparedStatement.executeUpdate();
            connection.close();

        }catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }
}
