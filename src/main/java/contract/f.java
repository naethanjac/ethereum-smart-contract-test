package contract;

import java.io.File;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer.*;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;
import org.web3j.utils.Numeric;

import static org.web3j.tx.Transfer.GAS_LIMIT;
import static org.web3j.tx.gas.DefaultGasProvider.GAS_PRICE;

public class f {
    public static void main(String[]args){
        String walletPath = "/Users/apple/Library/Ethereum/rinkeby/keystore/UTC--2019-03-19T12-32-54.242898000Z--55bdf489902f4906e7bbda71272cfd0bf8b5c245";
        try {
            Web3j web3 = Web3j.build(new HttpService("https://rinkeby.infura.io/50a3bc111b7e49628fa020a48bb58cc7"));  // defaults to http://localhost:8545/
            Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().send();
            String clientVersion = web3ClientVersion.getWeb3ClientVersion();
            System.out.println(clientVersion);
            Credentials credentials = WalletUtils.loadCredentials("1234567890",walletPath);
            System.out.println(credentials.getAddress());
            Split contract = Split.load("0xbBeC71115a7cEcaCbfed73AEe0604f1C12C71100", web3, credentials, Split.GAS_PRICE, Split.GAS_LIMIT);
            System.out.println(contract.isValid());
            //TransactionReceipt transactionReceipt = contract.withdraw().send();
            //System.out.println(transactionReceipt.getBlockHash());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
