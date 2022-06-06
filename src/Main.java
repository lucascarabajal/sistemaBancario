
import models.Cliente;
import models.Cuenta;
import models.Sucursal;
import service.*;

import java.sql.Date;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        IClienteService clienteService = new ClienteService();
        ISucursalService sucursalService = new SucursalService();
        IBancoService bancoService = new BancoService();
        ICuentaService cuentaService = new CuentaService();

        boolean continuar = true;

        do{
            System.out.print("\033[H\033[2J");
            System.out.flush();
            menu();

            Scanner scanner = new Scanner(System.in);
            boolean error = false;
            Integer option = null;

            //Validación de datos de entradas.
            do{
                try {
                    option = scanner.nextInt();
                    if (option>0 && option<14){
                        error = false;
                    }else {
                        System.out.println("Debe ingresar una opción válida.");
                        error = true;
                    }
                }catch (InputMismatchException e){
                    System.out.println("Debe de ingresar una opción válida.");
                    error = true;
                    scanner.nextLine();
                }
            }while (error);

            switch (option){
                //Add cliente
                case 1:
                    addCliente(clienteService, sucursalService, scanner);
                    break;

                    //Add cuenta
                case 2:
                    addCuenta(clienteService, bancoService, cuentaService, scanner);
                    break;

                    //Mostrar cliente
                case 3:
                    listCliente(clienteService, scanner);
                    break;

                    //Listar clientes por sucursal
                case 4:
                    scanner.nextLine();
                    clienteService.listClientes().forEach(System.out::println);
                    scanner.nextLine();
                    break;

                    //Listar clientes de una sucursal
                case 5:
                    listClientes(clienteService, sucursalService, scanner);
                    break;

                    //Extraer dinero
                case 6:
                    extraerDinero(cuentaService, scanner);
                    break;

                    //Consultar Saldo
                case 7:
                    consultarSaldo(cuentaService, scanner);
                    break;

                    //Realizar depósito
                case 8:
                    depositar(cuentaService, scanner);
                    break;

                    //Realizar Transferencia
                case 9:
                    transferencia(cuentaService, scanner);
                    break;

                    //Agregar Sucursal
                case 10:
                    addSucursal(sucursalService, scanner);
                    break;

                    //Eliminar Sucursal
                case 11:
                    deleteSucursal(sucursalService, scanner);
                    break;

                    //Listar Sucursales
                case 12:
                    scanner.nextLine();
                    sucursalService.listSucursales().forEach(System.out::println);
                    scanner.nextLine();
                    break;

                case 13:
                    scanner.nextLine();
                    continuar = false;

            }

        }while (continuar);
    }

    private static void deleteSucursal(ISucursalService sucursalService, Scanner scanner) {
        Integer idSucursal;
        scanner.nextLine();
        System.out.println("Ingrese el id de la sucursal que quiera eliminar: ");
        sucursalService.listSucursales().forEach(System.out::println);
        idSucursal = scanner.nextInt();
        System.out.println("Ingrese el id de la sucursal que quiere pasar todos sus clientes: ");
        Integer idSucursal1 = scanner.nextInt();
        sucursalService.deleteSucursal(sucursalService.findSucursalById(idSucursal), sucursalService.findSucursalById(idSucursal1));
        scanner.nextLine();
    }

    private static void addSucursal(ISucursalService sucursalService, Scanner scanner) {
        scanner.nextLine();
        System.out.println("Ingrese el domicilio de la sucursal: ");
        String domicilio = scanner.nextLine();

        Sucursal sucursal = new Sucursal(domicilio);
        sucursalService.addSucursal(sucursal,1);
        scanner.nextLine();
    }

    private static void transferencia(ICuentaService cuentaService, Scanner scanner) {
        Integer idCuenta;
        String dniCliente;
        Double monto;
        scanner.nextLine();
        System.out.println("Ingrese su DNI para ver las cuentas en la que quiera realizar la operación: ");
        dniCliente = scanner.nextLine();

        System.out.println("Ingrese una de las cuentas para realizar el deposito.");
        cuentaService.listarCuentaByDni(dniCliente).forEach(System.out::println);
        idCuenta = scanner.nextInt();

        System.out.println("Ingrese el CBU de la cuenta a la que quiere realizar la transferencia.");
        Long cbu = scanner.nextLong();

        System.out.println("Ingrese el monto a transferir: ");
        monto = scanner.nextDouble();

        cuentaService.transferir(cuentaService.findCuentaById(idCuenta),cbu,monto);
        scanner.nextLine();
    }

    private static void depositar(ICuentaService cuentaService, Scanner scanner) {
        Integer idCuenta;
        String dniCliente;
        Double monto;
        scanner.nextLine();
        System.out.println("Ingrese su DNI para ver las cuentas en la que quiera realizar la operación: ");
        dniCliente = scanner.nextLine();

        System.out.println("Ingrese una de las cuentas para realizar el deposito.");
        cuentaService.listarCuentaByDni(dniCliente).forEach(System.out::println);
        idCuenta = scanner.nextInt();

        System.out.println("Ingrese el monto a depositar: ");
        monto = scanner.nextDouble();

        cuentaService.depositar(cuentaService.findCuentaById(idCuenta), monto);
        scanner.nextLine();
    }

    private static void consultarSaldo(ICuentaService cuentaService, Scanner scanner) {
        Integer idCuenta;
        String dniCliente;
        scanner.nextLine();
        System.out.println("Ingrese su DNI para ver las cuentas en la que quiera realizar la operación: ");
        dniCliente = scanner.nextLine();

        System.out.println("Ingrese una de las cuentas para realizar la consulta.");
        cuentaService.listarCuentaByDni(dniCliente).forEach(System.out::println);
        idCuenta = scanner.nextInt();

        System.out.println("Su saldo es de $"+ cuentaService.consultarSaldo(cuentaService.findCuentaById(idCuenta)));
        scanner.nextLine();
    }

    private static void extraerDinero(ICuentaService cuentaService, Scanner scanner) {
        String dniCliente;
        scanner.nextLine();
        System.out.println("Ingrese su DNI para ver las cuentas en la que quiera realizar la operación: ");
        dniCliente = scanner.nextLine();

        System.out.println("Ingrese una de las cuentas para realizar la extracción.");
        cuentaService.listarCuentaByDni(dniCliente).forEach(System.out::println);

        Integer idCuenta = scanner.nextInt();
        System.out.println("Ingrese el monto a extraer: ");
        Double monto = scanner.nextDouble();

        cuentaService.extraer(cuentaService.findCuentaById(idCuenta),monto);
        scanner.nextLine();
    }

    private static void listCliente(IClienteService clienteService, Scanner scanner) {
        String dniCliente;
        scanner.nextLine();
        System.out.println("Ingrese el dni del cliente: ");
        dniCliente = scanner.nextLine();
        System.out.println(clienteService.findByClienteByDni(dniCliente));
        scanner.nextLine();
    }

    private static void addCuenta(IClienteService clienteService, IBancoService bancoService, ICuentaService cuentaService, Scanner scanner) {
        boolean error;
        scanner.nextLine();
        System.out.println("Ingrese el DNI del cliente para abrir la cuenta: ");
        String dniCliente = scanner.nextLine();
        System.out.println("Ingrese que tipo de cuenta quiere abrir: ");
        System.out.println("1- Cuenta Corriente");
        System.out.println("2- Caja Ahorro");
        Integer tipoCuenta =  null;
        error = false;
        do{
            try {
                tipoCuenta = scanner.nextInt();
                if (tipoCuenta>0 && tipoCuenta<3){
                    error = false;
                }else {
                    System.out.println("Debe de ingresar una opción válida.");
                    error = true;
                }
            }catch (InputMismatchException e){
                System.out.println("Debe de ingresar una opción válida.");
                error = true;
                scanner.nextLine();
            }
        }while (error);

        System.out.println("Ingrese el tipo de Moneda: ");
        System.out.println("1- Pesos Argentinos");
        System.out.println("2- Dolares.");
        Integer tipoMoneda = null;
        error = false;
        do{
            try {
                tipoMoneda = scanner.nextInt();
                if (tipoMoneda>0 && tipoMoneda<3){
                    error = false;
                }else {
                    System.out.println("Debe de ingresar una opción válida.");
                    error = true;
                }
            }catch (InputMismatchException e){
                System.out.println("Debe de ingresar una opción válida.");
                error = true;
                scanner.nextLine();
            }
        }while (error);

        Cuenta cuenta = new Cuenta(tipoMoneda, tipoCuenta, bancoService.findBancoById(1), clienteService.findByClienteByDni(dniCliente));
        cuentaService.addCuenta(cuenta);

        System.out.println("\n ***** La cuenta se ha añadido correctamente ***** \n");
        scanner.nextLine();
    }

    private static void addCliente(IClienteService clienteService, ISucursalService sucursalService, Scanner scanner) {
        scanner.nextLine();
        System.out.println("\n Ingrese el nombre del cliente: ");
        String nombre = scanner.nextLine();
        System.out.println("\n Ingrese el apellido del cliente: ");
        String apellido = scanner.nextLine();
        System.out.println("Ingrese el dni del cliente: ");
        String dni = scanner.nextLine();
        System.out.println("Ingrese la fecha de nacimiento YYYY-MM-DD: ");
        Date fechaNac = Date.valueOf(scanner.nextLine());
        System.out.println("Ingrese Email del cliente: ");
        String email = scanner.nextLine();
        System.out.println("Ingrese el número de teléfono del cliente: ");
        String numTelefono = scanner.nextLine();
        System.out.println("---- Seleccione alguna de las siguientes sucursales -----");

        for (Sucursal s: sucursalService.listSucursales()){
            System.out.println(s.getId() + "- Sucursal de "+ s.getDireccion());
        }

        Integer idSucursal = scanner.nextInt();
        Date fechaAlta = new Date(Calendar.getInstance().getTime().getTime());
        Cliente cliente = new Cliente(nombre,apellido,dni,fechaNac,email,numTelefono,fechaAlta, sucursalService.findSucursalById(idSucursal));
        clienteService.addCliente(cliente);

        System.out.println("\n ***** El cliente se ha añadido correctamente ***** \n");
        scanner.nextLine();
    }

    private static void listClientes(IClienteService clienteService, ISucursalService sucursalService, Scanner scanner) {
        Integer idSucursal;
        scanner.nextLine();
        System.out.println("---- Seleccione alguna de las siguientes sucursales -----");

        for (Sucursal s: sucursalService.listSucursales()){
            System.out.println(s.getId() + "- Sucursal de "+ s.getDireccion());
        }

        idSucursal = scanner.nextInt();
        clienteService.listClientesBySucursal(sucursalService.findSucursalById(idSucursal)).forEach(System.out::println);
        scanner.nextLine();
    }

    public static void menu(){
        System.out.println("*********************************");
        System.out.println("********** Bienvenido ***********");
        System.out.println("*********************************");
        System.out.println("------- Elija una opcion: -------");
        System.out.println("1) Agregar Cliente");
        System.out.println("2) Agregar cuenta a Cliente");
        System.out.println("3) Mostrar Cliente");
        System.out.println("4) Listar Clientes por sucursal");
        System.out.println("5) Listar Clientes de una sucursal");
        System.out.println("6) Extraer dinero");
        System.out.println("7) Consultar Saldo");
        System.out.println("8) Realizar Deposito");
        System.out.println("9) Realizar transferencias");
        System.out.println("10) Agregar Sucursal");
        System.out.println("11) Eliminar una sucursal");
        System.out.println("12) Listar Sucursales del Banco");
        System.out.println("13) Exit");
        System.out.println("*********************************");
    }

}
