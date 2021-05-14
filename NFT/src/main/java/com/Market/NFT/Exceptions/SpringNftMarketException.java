package com.Market.NFT.Exceptions;

    public class SpringNftMarketException extends RuntimeException {
        public SpringNftMarketException(String exMessage, Exception exception) {
            super(exMessage, exception);
        }

        public SpringNftMarketException(String exMessage) {
            super(exMessage);
        }
    }

