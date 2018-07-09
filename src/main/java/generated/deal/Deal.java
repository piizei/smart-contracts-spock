package generated.deal;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.4.0.
 */
public class Deal extends Contract {
    private static final String BINARY = "6060604052341561000f57600080fd5b60008054600160a060020a033316600160a060020a03199091161790556102138061003b6000396000f3006060604052600436106100775763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166308551a53811461007c5780631054e059146100ab57806341c0e1b5146100c05780637150d8ae146100d3578063a0ac6ac3146100e6578063ae7850b91461010b575b600080fd5b341561008757600080fd5b61008f610121565b604051600160a060020a03909116815260200160405180910390f35b34156100b657600080fd5b6100be610130565b005b34156100cb57600080fd5b6100be610189565b34156100de57600080fd5b61008f6101b2565b34156100f157600080fd5b6100f96101c1565b60405190815260200160405180910390f35b341561011657600080fd5b6100be6004356101c7565b600054600160a060020a031681565b60005433600160a060020a039081169116141561014c57600080fd5b600154600160a060020a03161515610187576001805473ffffffffffffffffffffffffffffffffffffffff191633600160a060020a03161790555b565b60005433600160a060020a039081169116146101a457600080fd5b600054600160a060020a0316ff5b600154600160a060020a031681565b60025481565b60005433600160a060020a039081169116146101e257600080fd5b6002555600a165627a7a72305820e9a36760b2732bb7b501085105fbbb5b3abae70496d6fb34a24684263582d0790029";

    public static final String FUNC_SELLER = "seller";

    public static final String FUNC_SIGNDEAL = "signDeal";

    public static final String FUNC_KILL = "kill";

    public static final String FUNC_BUYER = "buyer";

    public static final String FUNC_DEALHASH = "dealHash";

    public static final String FUNC_CREATEDEAL = "createDeal";

    protected Deal(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Deal(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<String> seller() {
        final Function function = new Function(FUNC_SELLER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> signDeal() {
        final Function function = new Function(
                FUNC_SIGNDEAL, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> kill() {
        final Function function = new Function(
                FUNC_KILL, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> buyer() {
        final Function function = new Function(FUNC_BUYER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<byte[]> dealHash() {
        final Function function = new Function(FUNC_DEALHASH, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<TransactionReceipt> createDeal(byte[] _dealHash) {
        final Function function = new Function(
                FUNC_CREATEDEAL, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(_dealHash)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static RemoteCall<Deal> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Deal.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Deal> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Deal.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static Deal load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Deal(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Deal load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Deal(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
