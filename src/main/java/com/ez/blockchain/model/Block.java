package com.ez.blockchain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 区块结构
 *
 * @author Ethan.Z
 * @date 2022-09-19 17:34
 */
@Data
@AllArgsConstructor
public class Block {

    /**
     * 索引号
     */
    private int index;

    /**
     * 当前区块的hash值, 区块的唯一标识
     */
    private String hash;

    /**
     * 前一个区块的hash值
     */
    private String preHash;

    /**
     * 随机数, 要求解的整数值
     */
    private int nonce;

    /**
     * 生成区块的时间戳
     */
    private Long timestamp;

    /**
     * 当前区块的交易集合
     */
    private List<Transaction> transactions;
}
