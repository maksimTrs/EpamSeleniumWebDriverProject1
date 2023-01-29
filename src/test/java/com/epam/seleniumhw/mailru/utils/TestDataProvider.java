package com.epam.seleniumhw.mailru.utils;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import static com.epam.seleniumhw.mailru.utils.NumberGenerator.*;



public class TestDataProvider {



    private static String firstDomainLvl = getRandomFirstDomainLvl();

    private static int randomGroupNumberGenerator = (int) (Math.random() * 100 + 3);
    private static String groupName = "GroupName_" + generateNumber() + "_" + randomGroupNumberGenerator;
    private static String groupHeader = "GroupHeader_1_" + randomGroupNumberGenerator;
    private static String groupFooter = "GroupFooter_1_" + randomGroupNumberGenerator;



    @DataProvider(name = "data-provider")
    public static Object[][] dataProviderForGroup() {
        return new Object[][]{
                {groupName + getIntRandomValueForMobile(7, 2), groupHeader, groupFooter},
                {groupName + getIntRandomValueForMobile(117, 4), groupHeader, groupFooter},
                {groupName + getIntRandomValueForMobile(1117, 8), groupHeader, groupFooter}
        };
    }

}
