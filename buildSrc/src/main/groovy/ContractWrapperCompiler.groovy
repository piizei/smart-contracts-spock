import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.web3j.codegen.SolidityFunctionWrapper

import javax.script.ScriptEngineManager

/*
    Inspired by https://github.com/web3j/web3j-maven-plugin/blob/master/src/main/java/org/web3j/mavenplugin/
 */

class ContractWrapperCompiler extends DefaultTask {

    final static String SOLIDITY_TEMP = "build/tmp/solidity-output"
    final static String GENERATED_DIR = "src/main/java"

    @TaskAction
    def run() {
        new File(SOLIDITY_TEMP).traverse {
            if (it.name.endsWith("json")) {
                def contracts = extractContracts it.text
                if (contracts) {
                    contracts.each { k,v ->
                        new SolidityFunctionWrapper(true).generateJavaFiles(
                                k.split(':')[1],
                                v.get("bin"),
                                v.get("abi"),
                                GENERATED_DIR,
                                "generated."+k.split(':')[1].toLowerCase())
                    }
                } else {
                    println "No Compiled Solidity Contracts found for Contract Wrapper Generation"
                }
            }
        }
    }

    static Map<String, Map<String, String>> extractContracts(String contract) {
        def engine = new ScriptEngineManager().getEngineByName("nashorn")
        String script = "JSON.parse(JSON.stringify($contract))"
        Map<String, Object> json = (Map<String, Object>) engine.eval(script)
        return (Map<String, Map<String, String>>) json.contracts
    }
}
