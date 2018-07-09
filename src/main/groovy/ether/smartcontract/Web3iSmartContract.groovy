package ether.smartcontract

import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.RemoteCall
import org.web3j.utils.Numeric

import static org.web3j.crypto.Hash.sha3String

trait Web3iSmartContract {

    def client
    Class contractClass
    def deploy() {
        def result = contractClass.deploy(client.client, client.credentials,
                client.networkGasPrice,
                (client.networkGasLimit * gasLimitMultiplier).toBigInteger())
                .send()
        address=result.contractAddress
        load(address)
        result
    }

    def load = { String address ->
        contractImpl = contractClass.load(
                address,
                (Web3j) client.client,
                (Credentials) client.credentials,
                client.networkGasPrice,
                (client.networkGasLimit * gasLimitMultiplier).toBigInteger())
        return this
    }

    def methodMissing(String name, args) {
        def response
        if(args) {
            response = contractImpl."$name"(*args)
            if(response instanceof RemoteCall) {
                response = response.send()
            }
        } else {
            response = contractImpl."$name"()
            if(response instanceof RemoteCall) {
                response = response.send()
            }
        }
        return response
    }

    static Byte[] hashString(String string) {
        // Todo: Using byte -> Byte wrapper because of GROOVY-8560
        Numeric.hexStringToByteArray(sha3String( string )) as Byte []
    }
}
