import com.fasterxml.jackson.databind.ObjectMapper
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.web3j.crypto.ECKeyPair
import org.web3j.crypto.Wallet
import org.web3j.crypto.WalletUtils

class EthereumWallet extends DefaultTask {

    private static final ObjectMapper objectMapper = new ObjectMapper()
    private static final DEFAULT_PASSWORD = "insecure"
    private static final DEFAULT_WALLET_NAME = "test-wallet.json"
    private static final DEFAULT_WALLET_DIR = "src/test/resources"

    @Input
    String password = DEFAULT_PASSWORD
    @Input
    String walletName = DEFAULT_WALLET_NAME
    @Input
    String walletDir = DEFAULT_WALLET_DIR

    @TaskAction
    def run() {
        File destination = new File(walletDir, walletName)
        if (!destination.exists()) {
            def wf = Wallet.createLight(password,
                    ECKeyPair.create(new BigInteger(32, new Random())))
            objectMapper.writeValue(destination, wf)
        } else {
            println "Test wallet available, not overwriting"
        }

    }

    static getAccountAddress(
            def walletName = DEFAULT_WALLET_NAME,
            def walletDir = DEFAULT_WALLET_DIR,
            def password = DEFAULT_PASSWORD
    ) {
        return WalletUtils.loadCredentials(password,
                "${walletDir}/${walletName}").address
    }
}
