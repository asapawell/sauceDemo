package ru.work;
import org.testng.annotations.DataProvider;
public class DataProviderClass {
    @DataProvider(name = "loginsDataProvider")
    public Object[][] getUsers(){
        return new Object[][]
                {
                        {"standard_user", "secret_sauce"},
                        {"locked_out_user", "secret_sauce"},
                        {"problem_user", "secret_sauce"},
                        {"performance_glitch_user", "secret_sauce"}
                };
    }
    @DataProvider(name = "itemsDataProvider")
    public Object[][] getItems(){
        return new Object[][]
                {
                        {1, "Sauce Labs Bike Light", "2"},
                        {2, "Sauce Labs Bolt T-Shirt", "3"},
                        {3, "Sauce Labs Fleece Jacket", "4"},
                        {4, "Sauce Labs Onesie", "5"},
                        {5, "Test.allTheThings() T-Shirt (Red)", "6"}
                };
    }
}
