interface CustomerSevice {

    public void addNewCustomer();

    public void searchUser(String ID);

    public void editUserInfo(String ID);

    public void deleteUser(String ID);

    public void displayCustomersData(boolean showHeader);

    public void addNewFlightToCustomerList(Flight f);

    public void addExistingFlightToCustomerList(int index, int numOfTickets);

}
