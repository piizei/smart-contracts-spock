import ether.clients.InfuraClient
import org.web3j.utils.Convert


def supplier = InfuraClient.createFromFile('./src/test/resources/supplier.properties')
def purchaser = InfuraClient.createFromFile('./src/test/resources/purchaser.properties')

def transactionPrice = supplier.networkGasPrice * supplier.networkGasLimit

if (supplier.balance < transactionPrice) {
    println """
        Supplier wallet does not have enough Ether to proceed. 
        Please check the README how to generate in a test network.
    """
    System.exit(1)
}

if (purchaser.balance < transactionPrice && supplier.balance > Convert.toWei(1,Convert.Unit.ETHER)) {
    println """
        Purchacer does not have enough Ether to proceed.
        Transfering 1 ETH from Supplier wallet to Purchaser wallet.
    """
    supplier.sendEther(purchaser.credentials.address, BigDecimal.valueOf(1))
    println "1 ETH was transferred \uD83D\uDCB0"
} else {
    println "All required wallet conditions are met \uD83D\uDC4C"
}
