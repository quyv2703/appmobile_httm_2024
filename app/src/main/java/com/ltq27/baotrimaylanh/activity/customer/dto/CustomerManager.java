    package com.ltq27.baotrimaylanh.activity.customer.dto;


    public class CustomerManager {
        private static CustomerManager instance;
        private CustomerDTO user;

        private CustomerManager() {}

        public static synchronized CustomerManager getInstance() {
            if (instance == null) {
                instance = new CustomerManager();
            }
            return instance;
        }

        public void setUser(CustomerDTO user) {
            this.user = user;
        }

        public CustomerDTO getUser() {
            return user;
        }


    }
