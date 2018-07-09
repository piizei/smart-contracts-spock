package ether.smartcontract

class SmartContract implements Web3iSmartContract {

    def contractImpl
    // Control amount of gas used in transactions. You cannot use more than 1.0.
    // if you supply less, your transaction might take more time.
    def gasLimitMultiplier = 0.1
    def address

}
