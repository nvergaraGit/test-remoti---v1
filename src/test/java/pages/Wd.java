package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//import com.sun.corba.se.impl.util.Version;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.Excel;

import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.TakesScreenshot;

public class Wd {
    protected static final String CUR_DIR = System.getProperty("user.dir");
    public final static String BROWSER = System.getProperty("BROWSER", "chrome");
    public static WebDriver driver;
    public static WebDriver driver1;
    private static long timeout = 60;

    public static WebDriver setupDriver() {

        try {
            if (BROWSER.equals("firefox")) {

                WebDriver driver = null;
                String path1 = CUR_DIR + "\\Drivers\\firefox\\geckodriver.exe";
                System.setProperty("webdriver.gecko.driver", path1);
                DesiredCapabilities cap = new DesiredCapabilities();
                //cap.setCapability("browser.privatebrowsing.autostart", true);
                cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                cap.setCapability("acceptInsecureCerts", false);
                driver = new FirefoxDriver(cap);
                return driver;

            } else if (BROWSER.equals("docker")) {

                /*
                DesiredCapabilities caps = new DesiredCapabilities();
                driver = new RemoteWebDriver(new URL("http://localhost:4441/wd/hub"), caps);
                return driver;
*/

                DesiredCapabilities capabilities = DesiredCapabilities.chrome();

                /*
                capabilities.setCapability("browserName", "chrome");
                capabilities.setCapability("version", "70.0");
                capabilities.setCapability("platform", "win10"); // If this cap isn't specified, it will just get any available one.
                capabilities.setCapability("build", "LambdaTestSampleApp");
                capabilities.setCapability("name", "LambdaTestJavaSample");
                capabilities.setCapability("network", true); // To enable network logs
                capabilities.setCapability("visual", true); // To enable step by step screenshot
                capabilities.setCapability("video", true); // To enable video recording
                capabilities.setCapability("console", true); // To capture console logs
*/

                capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
                capabilities.setCapability("zal:tz", "America/Los_Angeles");
                driver = new RemoteWebDriver(new URL("http://localhost:4441/wd/hub"), capabilities);
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

                return driver;

            } else if (BROWSER.equals("chrome")) {


// LOCAL
                WebDriver driver = null;
                String path2 = CUR_DIR + "\\Drivers\\Chrome\\chromedriver.exe";
                System.setProperty("webdriver.chrome.driver", path2);
                // ChromeOptions chromeOptions = new ChromeOptions();
                // chromeOptions.addArguments("--start-maximized");
                // driver = new ChromeDriver(chromeOptions);
                DesiredCapabilities cap = new DesiredCapabilities();
                //cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, false);
                //cap.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

                ChromeOptions options = new ChromeOptions();
                options.addArguments("incognito");
                /*
                options.addArguments("--enable-plugins");
                options.addArguments("--enable-web-security");
                options.addArguments("--disable-infobars", "--disable-features=EnableEphemeralFlashPermission");
                 */

                cap.setCapability(ChromeOptions.CAPABILITY, options);
                driver = new ChromeDriver(cap);
                return driver;
            } else {

                System.out.println("Tipo de Browser No Soportado");
            }
        } catch (Exception ex) {
            System.out.println("Error al ejecutar el Driver: " + ex.toString());
        }
        return null;
    }

//  public static void takeScreenShotTest(WebDriver driver, String imageName) {
//    String curDir = System.getProperty("user.dir");
//    File directory = new File(curDir + "/imagenes/" + BROWSER);
//    // File directory = new File( curDir +"/imagenes/");
//    // File directory = new File("c:\\imagenes\\firefox");
//    try {
//      if (directory.isDirectory()) {
//        // Toma la captura de imagen
//        File imagen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        // Mueve el archivo a la carga especificada con el respectivo
//        // nombre
////        FileUtils.copyFile(imagen,
//            new File(directory.getAbsolutePath() + "\\" + imageName + ".png"));
//      } else {
//        // Se lanza la excepcion cuando no encuentre el directorio
//        throw new IOException("ERROR : La ruta especificada no es un directorio!");
//      }
//    } catch (IOException e) {
//      // Impresion de Excepciones
//      e.printStackTrace();
//    }
//  }

    //

    public static void takeScreenShotTest(WebDriver driver, String imageName) {
        String curDir = System.getProperty("user.dir");
        File directory = new File(curDir + "/images/" + BROWSER);
        // File directory = new File( curDir +"/imagenes/");
        // File directory = new File("c:\\imagenes\\firefox");
        try {
            if (directory.isDirectory()) {
                // Toma la captura de imagen
                File imagen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                // Mueve el archivo a la carga especificada con el respectivo
                // nombre
                FileHandler.copy(imagen,
                        new File(directory.getAbsolutePath() + "\\" + imageName + ".png"));
            } else {
                // Se lanza la excepcion cuando no encuentre el directorio
                throw new IOException("ERROR : La ruta especificada no es un directorio!");
            }
        } catch (IOException e) {
            // Impresion de Excepciones
            e.printStackTrace();
        }
    }

    public static List<List<String>> leerArchivo(String nomArchivo, int hoja) throws IOException {

        List<List<String>> datosExcel = new ArrayList<List<String>>();
        datosExcel = Excel.getDatosHojaExcel(nomArchivo, hoja);

        return datosExcel;

    }

    public static String getReportConfigPath(String prop) {
        Properties properties = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("src/test/resources/config.properties");
            properties.load(input);

            String reportConfigPath = properties.getProperty(prop);
            if (reportConfigPath != null)
                return reportConfigPath;
            else
                throw new RuntimeException(
                        "Report Config Path not specified in the config.properties file for the Key " + prop);

        } catch (Exception e) {
            throw new RuntimeException("file not found");

        }

    }

    public static void main(String[] args) {

        Wd ws = new Wd();
        System.out.println(ws.getReportConfigPath("reportConfigPath"));
    }

    public static String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
        Date date = new Date();
        return (dateFormat.format(date)); // 2016/11/16 12:08:43
    }

    /**
     * Function: waitForLoad Description: Espera a que la pagina termine de cargar.
     *
     * @param () driver
     * @return boolean
     * @author Date: 03-05-2016
     **/
    public static void waitForLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver wd) {
                // this will tel if page is loaded
                return "complete"
                        .equals(((JavascriptExecutor) wd).executeScript("return document.readyState"));
            }
        };
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        // wait for page complete
        wait.until(pageLoadCondition);
        // lower implicitly wait time
        driver.manage().timeouts().implicitlyWait(4000, TimeUnit.MILLISECONDS);
    }

    /**
     * Function: waitforExistsObject Description:Espera que un objeto se cargue en la pagina, realiza
     * hasta 15 intentos para encontrar el objeto y realiza una pausa en la ejecuciÃ³n del script
     * durante 2500 milisegundos
     *
     * @param () driver, (WebElement) we
     * @param () titleVp, (String) detalleVp, (LogResult) logResul
     * @return boolean
     * @author Date update: 10-05-2016
     **/
    public static boolean waitforExistsObject(WebDriver driver, WebElement we, int cantIntentos,
                                              long sleepTimeMs) {

        Boolean existsObject = false;
        int cont = 0;
        try {

            // Mientas el objeto no exista y no se haya llegado al total de intentos de
            // busqueda del objeto, se espera y verifica que el objeto se despliege
            while (!existsObject && cont <= cantIntentos) {
                try {
                    // Verifica si esta desplegado el elemento
                    if (we.isDisplayed())
                        existsObject = true;

                } catch (Exception ex) {
                    //
                }
                // Se detiene la ejecucion del script segun tiempo determinado
                if (!existsObject)
                    Thread.sleep(sleepTimeMs);
                // Cuenta la cantidad de intentos de verificacion del elemento
                cont++;
            }
        } catch (Exception ex) {
            System.out.println("Fnc waitforExistsObject. error: " + ex.toString());
            return false;
        }

        return existsObject;

    }

    /**
     * Function: findObjectPresent Description: busca que el elemento se encuentre desplegado en la
     * pagina
     *
     * @param driver       (WebDriver) Driver con el cual se ejecuta la prueba
     * @param we           (WebElement) Elemento a buscar
     * @param titleVp      (String) titulo del punto de verificaci�n
     * @param detalleVp    (String) detalle del punto de verificacion
     * @param cantIntentos (int) nro de intentos que se realizara para buscar el objeto en la
     *                     pantalla, ya que a veces el objeto no es encontrado debido a que la pagina aun no
     *                     termina de cargar
     * @param sleepTimeMs  (long) tiempo de espera entre los intentos de busqueda del objecto
     * @param ()           variable del log de la prueba
     * @return boolean
     * @author Date: 02-06-2016
     **/
    public static boolean findObjectPresent(WebDriver driver, WebElement we, String titleVp,
                                            String detalleVp, int cantIntentos, long sleepTimeMs) {

        boolean objectPresent = false;

        try {
            // Espera que se cargue la pagina
            waitForLoad(driver);
            // Espera que se despligue el objeto
            objectPresent = waitforExistsObject(driver, we, cantIntentos, sleepTimeMs);

            if (objectPresent) {
                Objets en = new Objets();
                en.enmarcarObjeto(driver, we);
                System.out.println("VP " + titleVp + " " + detalleVp + " fue detectado correctamente");
                en.desenmarcarObjeto(driver, we);
            } else {
                System.out.println("VP " + titleVp + " " + detalleVp + " no fue detectado correctamente.");
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Warning VP " + titleVp + " warning: " + ex.toString());
            return false;
        }
        return objectPresent;
    }

    /**
     * @param (driver,WebElement we
     * @return boolean
     * @Description: Realiza scroll en la pagina para mostrar elemento
     * @Function: scrollTo
     */
    public static void scrollTo(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

}

