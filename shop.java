/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicaHerencia;

import java.util.Scanner;

/**
 *
 * @author christianramirez
 */
// clase mayor que contiene todo el archivo ( es diferente a la clase padre que hereda propiedades)
public class shop {

    // Constate que almacena el tamaño del array principal de vehiculos.
    public static final int ARRAYLENGTH = 5;

    public static void main(String[] args) {

        // array principal de vehiculos
        vehicle[] vehicles = new vehicle[ARRAYLENGTH];
         //metodo del menu 
        showMenu(vehicles);
    }

    public static void askReturnToMenu(vehicle[] vehicles) {
        Scanner in = new Scanner(System.in);
        Scanner str = new Scanner(System.in);
        System.out.println("Do you wish to return to the menu: y/n");
        char wish = str.nextLine().charAt(0);

        if (wish == 'y' || wish == 'Y') {
            showMenu(vehicles);
        } else if (wish == 'n' || wish == 'N') {
            System.out.println("You can stay here as much as you want");
        } else {
            System.out.println("Sorry an error occurred. Try again");
            askReturnToMenu(vehicles);
        }
    }

    public static void showMenu(vehicle[] vehicles) {
        Scanner in = new Scanner(System.in);
        Scanner str = new Scanner(System.in);
        // varibale de control de flujo del menu
        boolean controlMenu = true;
        System.out.println("###################################");
        System.out.println("#   Welcome to the CAR SHOP       #");
        System.out.println("###################################");
        System.out.println("Select an option type it's number");
        System.out.println("1 -> Sing up vehicle");
        System.out.println("2 -> Show array");
        System.out.println("3 -> Get circulation permit");
        System.out.println("4 -> Get zero emissions report");
        System.out.println("5 -> Get ALL circulation permits");
        System.out.println("6 -> Process vehicle sales");
        System.out.println("7 -> See list of vehicle acording to STATE");
        System.out.println("8 -> EXIT");
        do {
            // varibale
            int option = in.nextInt();
            switch (option) {
                case 1:
                    singUpVehicle(vehicles, in, str);
                    break;
                case 2:
                    showArray(vehicles);
                    askReturnToMenu(vehicles);
                    break;
                case 3:
                    getCirculationPermit(vehicles);
                    break;
                case 4:
                    ceroEmissionsReport(vehicles);
                    break;
                case 5:
                    getAllCirculationPermit(vehicles);
                    break;
                case 6:
                    processVehiSale(vehicles);
                    break;
                case 7:
                    seeVehiclesByState(vehicles);
                    break;
                case 8:
                    controlMenu = false;
                    break;
                default:
                    System.out.println("Not valid option try again.");
                    break;
            }

        } while (controlMenu);
        System.out.println("Thank you for passing by. Adeu");
    }

    //metodo que muestra el array
    public static void showArray(vehicle[] vehicles) {

        for (int i = 0; i < ARRAYLENGTH; i++) {
            if (vehicles[i] == null) {
            } else {
                System.out.println("###################################");
                System.out.println(i + 1 + "->" + vehicles[i].getModel() + " PLATE NUMBER -> " + vehicles[i].getNumberPlate());
                System.out.println("");
            }
        }

    }

    // metodo que muestra la lista de vehiculos por STATE (ACTIVITAT)
    public static void seeVehiclesByState(vehicle[] vehicles) {
        Scanner in = new Scanner(System.in);
        Scanner str = new Scanner(System.in);
        System.out.println("Choose what type of vehicles you want to see");
        System.out.println("1 -> ACTIVE");
        System.out.println("2 -> NOT ACTIVE");
        int askState = in.nextInt();
        System.out.println(askState);
        boolean searchState = true;
        // Dependiendo de la opcion seleccionada se buscara por los vehiculos activos o no
        switch (askState) {
            case 1:
                searchState = true;
                break;
            case 2:
                searchState = false;
                break;
            default:
                System.out.println("ERROR : Try again");
                seeVehiclesByState(vehicles);

        }
        for (int i = 0; i < ARRAYLENGTH; i++) {
            if (vehicles[i] != null) {
                if (vehicles[i].isState() == searchState) {
                    System.out.println(vehicles[i].toString());
                }
            }
        }
        askReturnToMenu(vehicles);
    }

    //metodo que procesa la venta de un vehiculo
    public static void processVehiSale(vehicle[] vehicles) {
        Scanner in = new Scanner(System.in);
        Scanner str = new Scanner(System.in);
        System.out.println("Vehicle selling process");
        //Usar el metodo ya creado que muestra el array.
        showArray(vehicles);
        System.out.println("Type the number of the vehicle to be sell");
        int optionNumber = in.nextInt();
        // Restar 1 al numero introducido por teclado para que coincida con el index del array
        optionNumber = optionNumber - 1;
        // Verificar si este objeto es de la clase vehicleElectric o Combustion
        if (vehicles[optionNumber] instanceof vehicleElectric) {
            //Si el objeto es de la clase vehicleElectric se realiza el casting
            vehicleElectric vehicleElecSale = (vehicleElectric) (vehicles[optionNumber]);
            vehicleElecSale.sell();
            System.out.println(vehicleElecSale.toString());

            System.out.println("SALE OF ELECTRIC CAR SUCCESSFULLY REGISTERED");
        } else if (vehicles[optionNumber] instanceof combustion) {
            //Si el objeto es de la clase combustion se realiza el casting
            combustion vehicleComSale = (combustion) (vehicles[optionNumber]);
            vehicleComSale.sell();
            System.out.println(vehicleComSale.toString());
            System.out.println("SALE OF COMBUSTION CAR SUCCESSFULLY REGISTERED");
        } else {
            System.out.println("Sorry an error occurred.");
        }
        askReturnToMenu(vehicles);
    }

    // metodo que genera todos los permisos de circulacion
    public static void getAllCirculationPermit(vehicle[] vehicles) {
        for (int i = 0; i < ARRAYLENGTH; i++) {
            if (vehicles[i] != null) {
                if (vehicles[i] instanceof vehicleElectric) {
                    vehicleElectric vehicleElecPermit = (vehicleElectric) (vehicles[i]);
                    vehicleElecPermit.circulationPermit();
                    System.out.println("MODEL -> " + vehicleElecPermit.getModel());
                    System.out.println("MODEL -> " + vehicleElecPermit.getNumberPlate());
                    System.out.println("CHARGE POWER -> " + vehicleElecPermit.getPowerChargekW());
                    System.out.println("###################################");
                } else if (vehicles[i] instanceof combustion) {
                    combustion vehicleComPermit = (combustion) (vehicles[i]);
                    vehicleComPermit.circulationPermit();
                    System.out.println("MODEL -> " + vehicleComPermit.getModel());
                    System.out.println("MODEL -> " + vehicleComPermit.getNumberPlate());
                    System.out.println("CILINDRADA -> " + vehicleComPermit.getCilindrada());
                    System.out.println("###################################");
                } else {
                    System.out.println("Sorry an error occurred.");
                }
            }
        }
        showMenu(vehicles);
    }

    // metodo que genera el informe de emision cero.
    public static void ceroEmissionsReport(vehicle[] vehicles) {
        Scanner in = new Scanner(System.in);
        Scanner str = new Scanner(System.in);
        System.out.println("Please introduce plate number:");
        String plateNumberCero = str.nextLine();
        plateNumberCero = plateNumberCero.trim();

        for (int i = 0; i < ARRAYLENGTH; i++) {
            // verifica si el objecto en la posicion i es de tipo vehicleElectric
            if (vehicles[i] instanceof vehicleElectric) {
                // casting de clase padre a la clase hijo
                vehicleElectric vehicleElec = (vehicleElectric) (vehicles[i]);
                //realiza la comparacion de matriculas
                if (vehicleElec.getNumberPlate().equalsIgnoreCase(plateNumberCero)) {
                    System.out.println("VEHICLE FOUND");
                    vehicleElec.ceroEmissions();
                    System.out.println("MODELO - > " + vehicleElec.getModel());
                    i = ARRAYLENGTH;

                }
            }
        }
        showMenu(vehicles);

    }

    // metodo que genera el permiso de circulacion
    public static void getCirculationPermit(vehicle[] vehicles) {
        Scanner in = new Scanner(System.in);
        Scanner str = new Scanner(System.in);
        System.out.println("Please introduce plate number:");
        String plateNumberPermit = str.nextLine();
        plateNumberPermit = plateNumberPermit.trim();
        //Bucle que recorre el array y solo realiza la comparacion si la posicon no es nula.
        for (int i = 0; i < ARRAYLENGTH; i++) {
            if (vehicles[i] != null) {
                if (vehicles[i].getNumberPlate().equalsIgnoreCase(plateNumberPermit)) {
                    vehicles[i].circulationPermit();
                    System.out.println("MODEL NAME -> " + vehicles[i].getModel());
                    System.out.println("PLATE NUMBER -> " + vehicles[i].getNumberPlate());
                    System.out.println("COLOR -> " + vehicles[i].getColor());
                    i = ARRAYLENGTH;
                }
            }
        }
        showMenu(vehicles);
    }

    // metodo que procesa el alta de un vehiculo, recibe el arrar principal y ambos Scanners    
    public static void singUpVehicle(vehicle[] vehicles, Scanner in, Scanner str) {
        System.out.println("Type model:");
        String model = str.nextLine();
        System.out.println("Type number plate");
        String numberPlate = str.nextLine();
        System.out.println("Type vehicle color");
        String color = str.nextLine();
        System.out.println("State will be set TRUE by defaul, can be changed later.");
        System.out.println("Type of vehicle");
        System.out.println("1 -> Electric");
        System.out.println("2 -> Combustion");
        int optionVehicle = in.nextInt();
        if (optionVehicle == 1) {
            System.out.println("Electric selected");
            System.out.println("Type battery capacity");
            int battCapacity = str.nextInt();
            System.out.println("Type electric consum in kWh");
            int comEleckWh = in.nextInt();
            System.out.println("Type autonomy");
            int autonomy = str.nextInt();
            System.out.println("Type power charge in kWh");
            int powerChargekW = in.nextInt();
            System.out.println("Type vehicle power in kWh");
            int powerVehikW = str.nextInt();
            vehicleElectric car = new vehicleElectric(battCapacity, comEleckWh, autonomy, powerChargekW, powerVehikW, model, numberPlate, color, true);
            for (int i = 0; i < ARRAYLENGTH; i++) {
                if (vehicles[i] == null) {
                    vehicles[i] = car;
                    i = ARRAYLENGTH;
                }
            }

        } else if (optionVehicle == 2) {
            System.out.println("Combustion selected");
            System.out.println("Type cilindrada");
            int cilindrada = in.nextInt();
            System.out.println("Type tipus motor");
            String tipusMotor = str.nextLine();
            System.out.println("Type litres consum");
            float consumLitres = str.nextFloat();
            combustion car = new combustion(cilindrada, tipusMotor, consumLitres, model, numberPlate, color, true);
            for (int i = 0; i < ARRAYLENGTH; i++) {
                if (vehicles[i] == null) {
                    vehicles[i] = car;
                    i = ARRAYLENGTH;
                }
            }
        } else {
            System.out.println("Sorry an error occurred.");
        }
        showMenu(vehicles);
    }
}

// clase padre que contiene las propiedades a heredar
class vehicle {

    // protected permite que se puedan acceder a estas variables desde otras clases.
    protected String model;
    protected String numberPlate;
    protected String color;
    protected boolean state;

    public vehicle() {
    }

    public vehicle(String model, String numberPlate, String color, boolean state) {
        this.model = model;
        this.numberPlate = numberPlate;
        this.color = color;
        this.state = state;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    // crear los metodos que comparten todas las clases
    public void sell() {
        System.out.println("Selling this vehicle:");
        // si se entra este metodo state cambia a falso porque el vehiculo se vendio
        this.state = false;
    }

    public void openIssue() {
        System.out.println("Opening Issue");
    }

    public void closeIssue() {
        System.out.println("Closing Issue");
    }

    public void circulationPermit() {
        System.out.println("Circulation permit generated for this vehicle:");
    }

    @Override
    public String toString() {
        return "vehicle{" + "model=" + model + ", numberPlate=" + numberPlate + ", color=" + color + ", state=" + state + '}';
    }

}

// extends <<nombre de clases>> indicia de que clase heredara propiedades.
// clase de vehiculos electricos que hereda propiedades de "vehicle"
class vehicleElectric extends vehicle {

    private int battCapacity;
    private int comEleckWh;
    private int autonomy;
    private int powerChargekW;
    private int powerVehikW;

    public vehicleElectric() {
        super();
    }

    public vehicleElectric(int battCapacity, int comEleckWh, int autonomy, int powerChargekW, int powerVehikW, String model, String numberPlate, String color, boolean state) {
        super(model, numberPlate, color, state);
        this.battCapacity = battCapacity;
        this.comEleckWh = comEleckWh;
        this.autonomy = autonomy;
        this.powerChargekW = powerChargekW;
        this.powerVehikW = powerVehikW;
    }

    public int getBattCapacity() {
        return battCapacity;
    }

    public void setBattCapacity(int battCapacity) {
        this.battCapacity = battCapacity;
    }

    public int getComEleckWh() {
        return comEleckWh;
    }

    public void setComEleckWh(int comEleckWh) {
        this.comEleckWh = comEleckWh;
    }

    public int getAutonomy() {
        return autonomy;
    }

    public void setAutonomy(int autonomy) {
        this.autonomy = autonomy;
    }

    public int getPowerChargekW() {
        return powerChargekW;
    }

    public void setPowerChargekW(int powerChargekW) {
        this.powerChargekW = powerChargekW;
    }

    public int getPowerVehikW() {
        return powerVehikW;
    }

    public void setPowerVehikW(int powerVehikW) {
        this.powerVehikW = powerVehikW;
    }

    public void ceroEmissions() {
        System.out.println("Zero emissions report generated for this vehicle:");
    }

    public void electricConsum() {
        System.out.println("Electric consum report");
    }

    @Override
    public String toString() {
        return "ELECTRIC VEHICLE { " + " model=" + model + ", numberPlate=" + numberPlate + ", color=" + color + ", state=" + state + ", battCapacity=" + battCapacity + ", comEleckWh=" + comEleckWh + ", autonomy=" + autonomy + ", powerChargekW=" + powerChargekW + ", powerVehikW=" + powerVehikW + '}';
    }

}

// clase de los vehiculos de combustion que hereda propiedades de "vehicle"
class combustion extends vehicle {

    private int cilindrada;
    private String tipusMotor;
    private float consumLitres;

    public combustion() {
        super();
    }

    public combustion(int cilindrada, String tipusMotor, float consumLitres, String model, String numberPlate, String color, boolean state) {
        super(model, numberPlate, color, state);
        this.cilindrada = cilindrada;
        this.tipusMotor = tipusMotor;
        this.consumLitres = consumLitres;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    public String getTipusMotor() {
        return tipusMotor;
    }

    public void setTipusMotor(String tipusMotor) {
        this.tipusMotor = tipusMotor;
    }

    public float getConsumLitres() {
        return consumLitres;
    }

    public void setConsumLitres(float consumLitres) {
        this.consumLitres = consumLitres;
    }

    public void carbonPolution() {
        System.out.println("Report of carbon polution");
    }

    @Override
    public String toString() {
        return "COMBUSTION VEHICLE { " + " model=" + model + ", numberPlate=" + numberPlate + ", color=" + color + ", state=" + state + ", cilindrada=" + cilindrada + ", tipusMotor=" + tipusMotor + ", consumLitres=" + consumLitres + '}';
    }

}
