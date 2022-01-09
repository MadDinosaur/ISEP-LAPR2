package app.domain.shared;

import app.controller.App;
import app.domain.model.Company;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Constants {
    public static final String ROLE_ADMIN = "ADMINISTRATOR";


    public static final String PARAMS_FILENAME = "config.properties";
    public static final String PARAMS_COMPANY_DESIGNATION = "Company.Designation";

    /**
     * Constant for the max words a report can have
     */
    public static final int REPORT_MAX_WORDS = 400;

    public static final int CATEGORY_NAME_MAX_CHARS = 15;
    public static final int CATEGORY_CODE_MIN_CHARS = 4;
    public static final int CATEGORY_CODE_MAX_CHARS = 8;


    public static final String SEX_BY_DEFAULT = "None";
    public static final String SEX_OPTION_1 = "Male";
    public static final String SEX_OPTION_2 = "Female";

    public static final Company company = App.getInstance().getCompany();
}
