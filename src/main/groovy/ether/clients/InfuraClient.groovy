package ether.clients

import org.web3j.crypto.Credentials
import org.web3j.crypto.WalletUtils
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.core.methods.response.EthGasPrice
import org.web3j.protocol.core.methods.response.TransactionReceipt
import org.web3j.protocol.http.HttpService
import org.web3j.tx.Transfer
import org.web3j.utils.Convert

// see infura.io
class InfuraClient {

    Credentials credentials
    String url
    Web3j web3j

    static InfuraClient createFromProperties(String propertyFileName) {
        def client = new InfuraClient()
        def props = new Properties()
        props.load(getClass().getResourceAsStream(propertyFileName))
        def config = new ConfigSlurper().parse(props)
        client.url = config.network.url
        client.credentials = WalletUtils.loadCredentials(config.wallet.password,
                config.wallet.file)
        client
    }

    static InfuraClient createFromFile(String fileName) {
        def client = new InfuraClient()
        def props = new Properties()
        props.load(new FileInputStream(fileName))
        def config = new ConfigSlurper().parse(props)
        client.url = config.network.url
        client.credentials = WalletUtils.loadCredentials(config.wallet.password,
                config.wallet.file)
        client
    }

    def getNetworkGasLimit() {
        return client.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false)
                .send().getBlock().gasLimit
    }

    Web3j getClient() {
        if(!web3j) {
            web3j = Web3j.build(new HttpService(url))
        }
        web3j
    }

    BigInteger getBalance() {
        client
                .ethGetBalance(credentials.address, DefaultBlockParameterName.LATEST)
                .sendAsync()
                .get().balance
    }

    BigInteger getNetworkGasPrice() {
        EthGasPrice ethGasPrice = client.ethGasPrice().send()
        ethGasPrice.gasPrice
    }

    boolean sendEther(String targetAddress, BigDecimal valueInEther) {
        Transfer.sendFunds(
                web3j, credentials, targetAddress,
                valueInEther, Convert.Unit.ETHER)
                .send().isStatusOK()

    }


}
