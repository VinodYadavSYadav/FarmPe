package com.renewin.Xohri;

public class Urls {

    public static final String Add_New_Address = "http://3.17.6.57:8080/api/MasterTable/AddUserAddress";
    private static final String ROOT_URL = " http://3.17.6.57:8686/api/Auth/";
    private static final String ROOT_URL1 = "http://3.17.6.57:8080/api/";
    private static final String ROOT_URL2 = "http://3.17.6.57:8585/api/";


    public static final String LOGIN=ROOT_URL+"ValidateUser";
    public static final String SIGNUP=ROOT_URL+"RegisterUser";
    public static final String GetAllCrops=ROOT_URL1+"Crops/GetCrops";
    public static final String GetWishListItems=ROOT_URL1+"Order/GetWishListItems";
    public static final String AddToCart=ROOT_URL1+"Order/AddToCart";
    public static final String GetOpenOrders=ROOT_URL1+"Order/GetOpenOrders";
    public static final String GetMyOrder=ROOT_URL1+"Order/GetMyOrder";
    public static final String GetCancelledOrders=ROOT_URL1+"Order/GetCancelledOrders";
    public static final String CartCount=ROOT_URL1+"Order/GetCartItemsCount";
    public static final String AddToWishList=ROOT_URL1+"Order/AddRemoveWishList";
    public static final String Languages=ROOT_URL1+"MasterTable/GetLanguages";
    public static final String getting_Languages=ROOT_URL1+"Lang/ChangeCurrentCulture";
    public static final String Forgot_Password=ROOT_URL+"ForgotPassword";
    public static final String ChangePassword=ROOT_URL+"ChangePassword";
    public static final String SetNewPassword=ROOT_URL+"UpdateUserPassword";
    public static final String ResendOTP=ROOT_URL+"ResendOTP";
    public static final String VerifyOTPNewUser=ROOT_URL+"VerifyOTPNewUser";
    public static final String GetCartItems=ROOT_URL1+"Order/GetCartItems";
    public static final String CancelOrder=ROOT_URL1+"Order/CancellOrder";
    public static final String CancelPlaceOrders=ROOT_URL1+"Order/CancellPlacedOrder";
    public static final String DeliveryPlaceOrder=ROOT_URL1+"Order/DeliverPlacedOrder";
    public static final String SaveLater=ROOT_URL1+"Order/CancellOrder";
    public static final String OrderPost=ROOT_URL1+"Order/AddOrderDetails";
    public static final String OrderList=ROOT_URL1+"Order/GetMyOrder";
    public static final String GET_ORDER_CATEGORIES="http://3.17.6.57:8585/api/MasterTable/GetCatagories";
    public static final String ORDER_SUB_CATEGORIES="http://3.17.6.57:8585/api/MasterTable/GetCrops";
    public static final String GetUserDetails=ROOT_URL+"GetUserDetails";
    public static final String ValidateReferalCode=ROOT_URL+"ValidateRefferalCode";
    public static final String PaytmChecksum="http://3.17.6.57:8080/api/Paytm/GetPaytmCheckSum";
    public static final String AddTransactionDetails="http://3.17.6.57:8080/api/Order/AddOrderPaymentDetails";

    public static final String PayuGetPaytmCheckSum="http://35.154.22.127:8585/api/Paytm/GetPaytmCheckSum";
    public static final String Get_New_Address = "http://3.17.6.57:8080/api/MasterTable/GetUserAddress";
    // Wallet
    public static final String GetWalletBalance = ROOT_URL +"MasterTable/GetWalletDetails";

    //My Account

    public static final String Edit_Profile_Details = ROOT_URL + "Auth/GetUserDetails";
    public static final String Update_Profile_Details = ROOT_URL + "Auth/UpdateUserProfile";


    public static final String Bank_Name_List= ROOT_URL + "MasterTable/GetBankLists";


    // Refer n Earn
    public static final String Refferal_Code = ROOT_URL +"Auth/GetUserDetails";
    public static final String ValidateRefferalCode = ROOT_URL +"Auth/ValidateRefferalCode";


    // Address

    public static final String Delete_Address_Details = ROOT_URL + "MasterTable/DeleteUserAddress";
    public static final String Default_Address = ROOT_URL + "MasterTable/UpdateUserDefaultAddress";





    public static final String Districts=ROOT_URL2+"MasterTable/GetDistricts";
    public static final String Taluks=ROOT_URL2+"MasterTable/GetTaluks";
    public static final String Hoblis=ROOT_URL2+"MasterTable/GetHoblis";
    public static final String Villages=ROOT_URL2+"MasterTable/GetVillages";
    public static final String State = ROOT_URL2+"MasterTable/GetStates";

    public static final String Get_Bank_Details = ROOT_URL1 +"MasterTable/GetUserBankDetails";
    public static final String Add_Bank_Details = ROOT_URL1 +"MasterTable/AddUpdateUserBankDetails";
    public static final String Delete_Bank_Details = ROOT_URL1+"MasterTable/DeleteBankDetails";
    public static final String UpdateDefaultAddress = ROOT_URL1+"MasterTable/UpdateUserDefaultAddress";
    public static final String TransactionDetails = ROOT_URL1+"MasterTable/GetTransactionsByUser";
    public static final String ViewOrderDetails = ROOT_URL2+"Order/GetOrderDetailsById";
    public static final String UpdateKYCDetails = ROOT_URL1+"MasterTable/GetUserKYCDetails";
    public static final String Add_KYC_Details = ROOT_URL1 +"MasterTable/AddUpdateUserKYCDetails";
    public static final String UpdateUserProfile = ROOT_URL +"UpdateUserProfile";
    public static final String GetBankNames = ROOT_URL1+"MasterTable/GetBankLists";
    public static final String GetWalletAmount = ROOT_URL1+"MasterTable/GetWalletDetails";
}
