package com.ez.blockchain;

import com.ez.blockchain.model.Block;
import com.ez.blockchain.model.Transaction;
import com.ez.blockchain.util.CryptoUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ethan.Z
 * @date 2022-09-19 17:57
 */
public class BlockServiceTest {

    /**
     * 模拟区块挖矿
     */
    @Test
    public void testBlockMine() {
        // 1 初始化
        // 1.1 创建区块链
        List<Block> blockChains = new ArrayList<>();
        // 1.2 创建创世区块
        Block genesisBlock = new Block(1, "1", "1", 1, System.currentTimeMillis(), new ArrayList<>());
        // 1.3 将创世区块加入区块链
        blockChains.add(genesisBlock);
        System.out.println(blockChains);

        // 2 挖矿
        // 2.1 创建系统奖励的交易
        Transaction sysTransaction = new Transaction();
        // 2.2 获取当前区块链里最后一个区块
        Block lastBlock = blockChains.get(blockChains.size() - 1);
        // 2.3 计算新区块Hash值, 便于模拟简化计算模型: Hash = SHA256(最后一个区块的Hash + 交易记录信息 + 随机数)
        List<Transaction> transactions = new ArrayList<>();
        Transaction tx1 = new Transaction();
        Transaction tx2 = new Transaction();
        Transaction tx3 = new Transaction();

        transactions.add(sysTransaction);
        transactions.add(tx1);
        transactions.add(tx2);
        transactions.add(tx3);

        int nonce = 1;

        String hash;
        while (true) {
            hash = CryptoUtil.SHA256(lastBlock.getHash() + transactions + nonce);

            // 2.4 校验Hash值
            if (hash.startsWith("0000")) {
                System.out.println("正确的挖矿hash结果:" + hash);
                System.out.println("计算次数:" + nonce);
                break;
            }

            System.out.println("错误的挖矿hash结果:" + hash);
            nonce++;
        }

        // 2.5 创建新的区块, 打包交易并将区块加入区块链表里
        Block newBlock = new Block(lastBlock.getIndex() + 1, hash, lastBlock.getHash(), nonce, System.currentTimeMillis(), transactions);
        blockChains.add(newBlock);
        System.out.println(blockChains);
    }
}
