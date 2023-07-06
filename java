import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonNavigationMenuTest {

    public static void main(String[] args) {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "path_to_chromedriver.exe");

        // Create a WebDriver instance
        WebDriver driver = new ChromeDriver();

        // Maximize the browser window
        driver.manage().window().maximize();

        // Launch the Amazon website
        driver.get("https://www.amazon.com/");

        // Test Case 1: Verify the presence of the navigation menu
        testNavigationMenuPresence(driver);

        // Test Case 2: Verify the main categories in the navigation menu
        testMainCategories(driver);

        // Test Case 3: Verify sub-categories in the navigation menu
        testSubCategories(driver);

        // Test Case 4: Verify navigation to sub-category pages
        testNavigationToSubcategory(driver);

        // Test Case 5: Verify navigation to department pages
        testNavigationToDepartment(driver);

        // Test Case 6: Verify navigation to sub-menu items
        testNavigationToSubmenuItem(driver);

        // Test Case 7: Verify navigation back to home page
        testNavigationBackToHomepage(driver);

        // Close the browser
        driver.quit();
    }

    private static void testNavigationMenuPresence(WebDriver driver) {
        try {
            // Check if the navigation menu element is present
            WebElement navigationMenu = new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.presenceOfElementLocated(By.id("nav-link-shopall")));
            if (navigationMenu.isDisplayed()) {
                System.out.println("Test Case 1: Passed - Navigation menu is present");
            } else {
                System.out.println("Test Case 1: Failed - Navigation menu is not present");
            }
        } catch (Exception e) {
            System.out.println("Test Case 1: Failed due to an exception: " + e.getMessage());
        }
    }

    private static void testMainCategories(WebDriver driver) {
        try {
            // Find all the main category elements
            java.util.List<WebElement> mainCategories = driver.findElements(By.xpath("//div[@id='nav-shop']/a[@class='nav-a']"));

            for (WebElement category : mainCategories) {
                // Hover over each main category element
                Actions actions = new Actions(driver);
                actions.moveToElement(category).perform();

                // Check if the sub-categories are displayed for each main category
                java.util.List<WebElement> subCategories = category.findElements(By.xpath(".//span[@class='nav-text']"));
                if (subCategories.size() > 0) {
                    System.out.println("Test Case 2: Passed - Main categories are displayed with sub-categories");
                } else {
                    System.out.println("Test Case 2: Failed - Main categories are not displayed with sub-categories");
                }
            }
        } catch (Exception e) {
            System.out.println("Test Case 2: Failed due to an exception: " + e.getMessage());
        }
    }

    private static void testSubCategories(WebDriver driver) {
        try {
            // Find all the main category elements
            java.util.List<WebElement> mainCategories = driver.findElements(By.xpath("//div[@id='nav-shop']/a[@class='nav-a']"));

            for (WebElement category : mainCategories) {
                // Hover over each main category element
                Actions actions = new Actions(driver);
                actions.moveToElement(category).perform();

                // Find the sub-category elements
                java.util.List<WebElement> subCategories = category.findElements(By.xpath(".//div[@class='nav-subcats']/a"));
                if (subCategories.size() > 0) {
                    System.out.println("Test Case 3: Passed - Sub-categories are displayed for each main category");
                } else {
                    System.out.println("Test Case 3: Failed - Sub-categories are not displayed for each main category");
                }
            }
        } catch (Exception e) {
            System.out.println("Test Case 3: Failed due to an exception: " + e.getMessage());
        }
    }

    private static void testNavigationToSubcategory(WebDriver driver) {
        try {
            // Find a main category element
            WebElement mainCategory = driver.findElement(By.xpath("//div[@id='nav-shop']/a[@class='nav-a'][1]"));

            // Hover over the main category element
            Actions actions = new Actions(driver);
            actions.moveToElement(mainCategory).perform();

            // Find a sub-category element
            WebElement subCategory = mainCategory.findElement(By.xpath(".//div[@class='nav-subcats']/a[1]"));

            // Click on the sub-category element
            subCategory.click();

            // Check if the user is redirected to the sub-category page
            if (driver.getCurrentUrl().contains("subcategory-page-url")) {
                System.out.println("Test Case 4: Passed - User is redirected to the sub-category page");
            } else {
                System.out.println("Test Case 4: Failed - User is not redirected to the sub-category page");
            }
        } catch (Exception e) {
            System.out.println("Test Case 4: Failed due to an exception: " + e.getMessage());
        }
    }

    private static void testNavigationToDepartment(WebDriver driver) {
        try {
            // Find a main category element
            WebElement mainCategory = driver.findElement(By.xpath("//div[@id='nav-shop']/a[@class='nav-a'][1]"));

            // Click on the main category element
            mainCategory.click();

            // Check if the user is redirected to the department page
            if (driver.getCurrentUrl().contains("department-page-url")) {
                System.out.println("Test Case 5: Passed - User is redirected to the department page");
            } else {
                System.out.println("Test Case 5: Failed - User is not redirected to the department page");
            }
        } catch (Exception e) {
            System.out.println("Test Case 5: Failed due to an exception: " + e.getMessage());
        }
    }

    private static void testNavigationToSubmenuItem(WebDriver driver) {
        try {
            // Find a main category element
            WebElement mainCategory = driver.findElement(By.xpath("//div[@id='nav-shop']/a[@class='nav-a'][1]"));

            // Hover over the main category element
            Actions actions = new Actions(driver);
            actions.moveToElement(mainCategory).perform();

            // Find a sub-category element
            WebElement subCategory = mainCategory.findElement(By.xpath(".//div[@class='nav-subcats']/a[1]"));

            // Hover over the sub-category element
            actions.moveToElement(subCategory).perform();

            // Find a sub-menu item element
            WebElement subMenuItem = subCategory.findElement(By.xpath(".//div[@class='nav-panel']/a[1]"));

            // Click on the sub-menu item element
            subMenuItem.click();

            // Check if the user is redirected to the sub-menu item page
            if (driver.getCurrentUrl().contains("submenu-item-page-url")) {
                System.out.println("Test Case 6: Passed - User is redirected to the sub-menu item page");
            } else {
                System.out.println("Test Case 6: Failed - User is not redirected to the sub-menu item page");
            }
        } catch (Exception e) {
            System.out.println("Test Case 6: Failed due to an exception: " + e.getMessage());
        }
    }

    private static void testNavigationBackToHomepage(WebDriver driver) {
        try {
            // Find the Amazon logo element
            WebElement amazonLogo = driver.findElement(By.id("nav-logo-sprites"));

            // Click on the Amazon logo element
            amazonLogo.click();

            // Check if the user is redirected back to the home page
            if (driver.getCurrentUrl().contains("www.amazon.com")) {
                System.out.println("Test Case 7: Passed - User is redirected back to the home page");
            } else {
                System.out.println("Test Case 7: Failed - User is not redirected back to the home page");
            }
        } catch (Exception e) {
            System.out.println("Test Case 7: Failed due to an exception: " + e.getMessage());
        }
    }
}
