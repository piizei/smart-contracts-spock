import ether.clients.InfuraClient
import ether.smartcontract.SmartContract
import ether.smartcontract.Web3iSmartContract
import generated.deal.Deal
import org.web3j.protocol.exceptions.TransactionException
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise


/*
 *  Specifications to the 'Deal'-ethereum-smart-contract
 */

@Stepwise
class DealSpec extends Specification {

    /*
     *  2 parties making the Deal, a Supplier and a Purchaser
     *  Both need their own ethereum wallets
     */
    @Shared
    def supplier = InfuraClient.createFromProperties('/supplier.properties')
    @Shared
    def purchaser = InfuraClient.createFromProperties('/purchaser.properties')

    /*
     *  Smart contract that is deployed to the ethereum network
     */
    @Shared
    SmartContract smartContract

    @Shared
    BigInteger maxTransactionPrice = 0

    @Shared
    String contractWordings = """
            So there was only one thing that I could do
            Was ding a ding dang my dang a long ling long
    """

    def "Supplier should have enough Ether to proceed with transactions"() {
        expect:
        balance > maxTransactionPrice

        where:
        balance = supplier.balance
    }

    def "Deploying Deal Contract to network should work"() {
        when:
        def receipt = smartContract.deploy()

        then:
        receipt.transactionReceipt.get().isStatusOK()

    }

    def "Supplier will hash the contract wordings and store it to the smart contract"() {
        when:
        def receipt = smartContract.createDeal(SmartContract.hashString(contractWordings))

        then:
        receipt.isStatusOK()
    }

    def "Supplier is not allowed to sign the contract"() {
        when:
        def receipt = smartContract.signDeal()

        then:
        thrown(TransactionException)
    }

    def "Purchaser validates wordings before signing"() {
        expect:
        hashFromNetwork == SmartContract.hashString(contractWordings)

        where:
        hashFromNetwork =
                new SmartContract([client: purchaser, contractClass: Deal.class])
                        .load(smartContract.address)
                        .dealHash()
    }

    def "Purchaser can sign the contract"() {
        expect:
        receipt.isStatusOK()

        where:
        receipt =
                new SmartContract([client: purchaser, contractClass: Deal.class])
                        .load(smartContract.address).signDeal()


    }

    def "Supplier can check if the contract was signed"() {
        expect:
        purchaserAddress == smartContract.buyer()

        where:
        purchaserAddress = purchaser.credentials.address
    }

    /*
        Boilerplate
     */


    def setupSpec() {
        maxTransactionPrice = supplier.networkGasPrice * supplier.networkGasLimit

        //Supplier sets up the smart contract
        smartContract = new SmartContract([client: supplier, contractClass: Deal.class])
    }

    /*
        Remove the smart contract from the chain after all specs are ran
     */

    def cleanupSpec() {
        smartContract.kill()
    }


}


