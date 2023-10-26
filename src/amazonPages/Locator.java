package amazonPages;
import java.time.Duration;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Locator {
 public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\software\\chromedriver-win32\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        driver.get("https://www.amazon.com/ ");
        driver.findElement(By.id("twotabsearchtextbox")).click();
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("watch");
        driver.findElement(By.id("nav-search-submit-button")).click();
        
        
     

        // Find the checkboxes using XPath
        driver.findElement(By.xpath("//*[@id='p_76/2661625011']/span/a/div[1]")).click();
        driver.findElement(By.xpath("//*[@id='p_89/Fossil']/span/a/div")).click();
       // driver.findElement(By.xpath("//*[@id='p_n_feature_four_browse-bin/24046373011']/span/a/div"))
               // .click();
        driver.findElement(By.xpath("//*[@id='p_n_availability/2661601011']/span/a/div")).click();

        // Locate and extract the product prices
        List<WebElement> priceElements = driver.findElements(By.xpath("//span[@class='a-price-whole']"));

        // Create a list to store the prices as numbers
        List<Double> prices = new ArrayList<>();

        // Extract the price values, convert to numbers, and store in the list
        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText();
            double price = Double.parseDouble(priceText);
            prices.add(price);
        }


        // Find the maximum and minimum prices
        double maxPrice = prices.stream().max(Double::compareTo).orElse(0.0);
        double minPrice = prices.stream().min(Double::compareTo).orElse(0.0);
      
     // Using DecimalFormat to format maxPrice and minPrice
        DecimalFormat df = new DecimalFormat("#");

        String maxPriceString = df.format(maxPrice);
        String minPriceString = df.format(minPrice);

        System.out.println("Maximum Price: $" + maxPriceString);
        System.out.println("Minimum Price: $" + minPriceString);

        // Printing the converted string
        System.out.println(maxPriceString);
        System.out.println(minPriceString); 

        // Dynamic value you want to insert into the XPath
        String dynamicValue = "169";

        // Create an XPath expression with the dynamic value
        String xpathExpression = "(//span[text()='" + maxPriceString + "'])[1]";
       // String xpathExpression1 = "(//span[text()='" + minPriceString + "'])[1]";
       
        // Use the XPath expression to locate and click the element
        

        driver.findElement(By.xpath(xpathExpression)).click();
     // driver.findElement(By.xpath(xpathExpression1)).click();

        // Find the index of the product with the maximum price
        int maxPriceIndex = prices.indexOf(maxPrice);
        int minPriceIndex = prices.indexOf(minPrice);
        
        
        

        // Add the product to the cart (Assuming "Add to Cart" button is present)
        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-button"));

        if (addToCartButton != null) {
            addToCartButton.click();
            System.out.println("Product added to the cart.");
        } else {
            System.out.println("Product not available.");
            
        }
        
        

//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//        WebElement addToCartButton1= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-to-cart-button")));
//   
//        if (addToCartButton1 != null) {
//            addToCartButton1.click();
//            System.out.println("Product with the minimum price added to the cart.");
//        } else {
//            System.out.println("Product with the minimum price not found or not available.");
//        }
//        
//        try {
//            WebElement element = driver.findElement(By.id("elementId"));
//            element.click();
//        } catch (TimeoutException e) {
//            System.out.println("Element not found within the specified time.");
//       }
        


        driver.navigate().back();
        driver.navigate().back(); 
        
        

     // Locate the "Add to Cart" button for the product with the minimum price    
     List<WebElement> addToCartButtons = driver.findElements(By.xpath("//span[text()='Add to Cart']"));

     if (minPriceIndex >= 1 && minPriceIndex < addToCartButtons.size()) {
         WebElement addToCartButton1 = addToCartButtons.get(minPriceIndex);
        // Thread.sleep(1500);
         addToCartButton1.click();
         System.out.println("Product with the minimum price added to the cart.");
     } else {
         System.out.println("Product with the minimum price not found or not available.");
     }

       // driver.quit();
    }
}
