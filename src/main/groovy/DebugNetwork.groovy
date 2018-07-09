import ether.clients.InfuraClient
import org.web3j.utils.Convert


def supplier = InfuraClient.createFromFile('./src/test/resources/supplier.properties')
def purchaser = InfuraClient.createFromFile('./src/test/resources/purchaser.properties')
def balanceS = supplier.balance
def balanceP = purchaser.balance
def gasPrice = supplier.networkGasPrice
def gasLimit = supplier.networkGasLimit
def trxPrice = gasLimit * gasPrice
println """
---------------------------------------------------------------
Account balance (supplier)   $balanceS wei (${Convert.fromWei(balanceS, Convert.Unit.ETHER)} ETH)
Account balance (purchaser)  $balanceP wei (${Convert.fromWei(balanceP, Convert.Unit.ETHER)} ETH)
Gas price                    $gasPrice wei (${Convert.fromWei(gasPrice, Convert.Unit.GWEI)} GWEI)
Gas limit                    $gasLimit wei (${Convert.fromWei(gasLimit, Convert.Unit.GWEI)} GWEI)
Transaction price            $trxPrice wei (${Convert.fromWei(trxPrice, Convert.Unit.ETHER)} ETH)
---------------------------------------------------------------
"""


System.exit(0)