# Testing Solidity/Ethereum smart contracts with Spock Framework

##Intro
I started this project after evaluating few tools for smart-contract development. 
It's based on the great [Spock framework](http://spockframework.org/)

Idea was to define the contract in the form of specification while developing it.
Test themselves are directly connected to Ethereum test network, as mocking would
make here even less sense than usual. Most of the problems that you wan't to discover
occur from the network.

## Design choices

### Docker
I just love docker in development as it helps to keep the dev environments uncluttered.

The compiler that compiles the solidity smart-contracts is run with docker.
If your system does not support docker, you can install it manually and change the 
_compileSolidity_ task in the _build.gradle_

### Gradle
Obvious choice with Spock...

### Groovy
What else? Maybe TypeScript, maybe next time.

## About this example

This is a about smart-contract that allows 2 people sign a Deal with specific wordings.
It is modelled according to [this specification](src/test/groovy/DealSpec.groovy)

The implementing contract is programmed as Solidity smart contract [here](src/main/solidity/deal.sol)

Rest of the project is really just dynamic glue code to bind the smart-contract and the specification together.
Under the hood it uses web3j to generate Java wrappers from the Solidity. The glue should be generic and
reusable to any smart-contract I might write in the future.

## Prerequisites to run this example

### Access to blockchain

You need access to some Ethereum blockchain network. For me it did not make sense to run
 own node just for testing as it can take a lot of disk space and could be slow to start.
 So I used [Infura](infura.io) which provides 
 API that the standard client libraries know how to connect. From development
 perspective it functions as you would have your own node.

1. Create Infura account (or other web3 compatible endpoint) and configure the address to [purchaser.properties](src/test/resources/purchaser.properties)
and [supplier.properties](src/test/resources/supplier.properties)

### Tokens to execute transactions on the blockchain

You need to pay GAS which is the cost of transaction when you create a smart-contract or execute any operations in the blockchain.
This is paid in Ether. 

To have ether, first you need a wallet. The wallet contains the address that is used as a key to pretty much everything.
This is kind of your identifier in the blockchain.
This project generates you 2 test wallets. You need 2 wallets, since we have two parties (Supplier and Purchaser) we need to
test in our smart-contract.

To generate test wallets, execute gradle command `gradle prepareFirstRun`

In development you can use test networks, where you can obtain Ether more easily without having to actually pay.
I used Rinkeby test network for which you can ask Ether from [here](https://www.rinkeby.io/#faucet)

You should ask the Ether for your Supplier-wallet. To see the wallet ether-address after you generated it,
execute gradle command ` gradle printSupplierWalletAddress`

After you have Ether in your wallet, you can execute the previously mentioned *prepareFirstRun* again
to transfer money from Supplier wallet to Purchaser wallet.

You can see transaction prices and other cool stats from the
Rinkeby test network [here](https://www.rinkeby.io/#stats)


Running the project:
-

After the following preconditions are met (Machine has docker, properties-files contain the web3 endpoints and at least the Supplier wallet has Ether as described above)

Unix/Mac:
```
./gradlew prepareFirstRun
./gradlew test

```

Win:
```
./gradlew.bat prepareFirstRun
./gradlew.bat test

````