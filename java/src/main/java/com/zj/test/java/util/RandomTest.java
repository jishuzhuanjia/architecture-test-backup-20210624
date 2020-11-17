package com.zj.test.java.util;

import com.zj.test.util.TestHelper;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/* @author: zhoujian
 * @qq: 2025513
 * @create-time: 2020/11/17 10:40
 * @description: Random随机数类测试
 * @version: 1.0
 * @finished: false
 * @finished-time:
 */
public class RandomTest {

    // 测试使用 - 随机数类对象
    Random random = new Random();

    /**
     * author: 2025513
     *
     * 1.nextInt(..)测试
     *
     * 1.1.public int nextInt()
     * 随机返回一个int值，所有2^32个int值都以(近似)相等的概率产生。
     *
     * 1.2.public int nextInt(int bound)
     * 返回[0,bound)中任意一个int值。
     * bound必须是正数，即bound>0,否则报错：java.lang.IllegalArgumentException: bound must be positive
     *
     * 测试思路:
     *
     * 结果:
     *
     * 结论:
     */
    @Test
    public void nextInt() {

        // public int nextInt()测试过程
        /*while (true) {
            TestHelper.println("nextInt()", random.nextInt());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/

        while (true) {
            //java.lang.IllegalArgumentException: bound must be positive
            //TestHelper.println("nextInt", random.nextInt(-5));

            TestHelper.println("nextInt(2)", random.nextInt(2));
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * author: 2025513
     *
     * 2.测试public boolean nextBoolean()
     * 等概率的随机返回true或false
     *
     * 测试思路:
     *
     *
     * 结论:
     */
    @Test
    public void nextBoolean() {
        while (true) {
            //java.lang.IllegalArgumentException: bound must be positive
            //TestHelper.println("nextInt", random.nextInt(-5));

            TestHelper.println("nextBoolean()", random.nextBoolean());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * author: 2025513
     *
     * 3.测试public double nextDouble()
     * 等概率的返回[0,1)之间的一个double值。
     *
     * 测试思路:
     *
     * 结果:
     * nextDouble(): 0.7085338973424046
     * nextDouble(): 0.34427545465342735
     * nextDouble(): 0.3525569712325015
     * nextDouble(): 0.29682520845803795
     * nextDouble(): 0.7235543987374959
     * nextDouble(): 0.5774405853197374
     * nextDouble(): 0.2510728852783247
     * nextDouble(): 0.2432636116874708
     * nextDouble(): 0.0863985927840506
     * nextDouble(): 0.2141053528211566
     *
     * 结论:
     */
    @Test
    public void nextDouble() {
        while (true) {

            TestHelper.println("nextDouble()", random.nextDouble());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * author: 2025513
     *
     * 4.测试public void nextBytes(byte[] bytesWillBeFilled)
     * 等概率随机产生byte值，并将bytesWillBeFilled数组填满
     *
     * 测试思路:
     *
     * 结果:
     * nextBytes(bytes): [-19, -58, 55, -31, -80, 104, -106, 0, 92, -29]
     * nextBytes(bytes): [18, 20, 58, -125, 20, -24, 15, -89, -110, 107]
     * nextBytes(bytes): [86, 60, -123, -63, 87, -109, 1, 74, -70, -70]
     * nextBytes(bytes): [87, -4, 88, 54, -31, 106, -93, -69, -103, 87]
     * nextBytes(bytes): [101, -80, 88, -104, -81, -88, 117, -68, 33, -56]
     * nextBytes(bytes): [-119, -35, -91, 58, 77, 53, 52, 44, -44, 20]
     * nextBytes(bytes): [3, 98, -58, -97, 121, 81, -13, 76, 31, -61]
     * nextBytes(bytes): [2, 17, 87, 78, -94, 85, -61, 0, -22, -29]
     * nextBytes(bytes): [-3, -37, 94, -98, 37, 18, 66, 51, -20, -21]
     * nextBytes(bytes): [99, 83, 123, 91, -55, 49, -55, 106, -44, 1]
     * nextBytes(bytes): [-123, 92, -5, 17, -125, -65, 23, 118, -93, 125]
     * nextBytes(bytes): [126, -103, 27, -83, -21, 90, 75, 82, 89, 25]
     * nextBytes(bytes): [-81, -53, -87, 68, -30, -68, 22, 52, -38, -47]
     * nextBytes(bytes): [-13, -60, 51, 41, 27, 63, 72, 48, -60, -121]
     * nextBytes(bytes): [-15, 112, -35, -82, 52, 64, 49, -110, 96, -110]
     * nextBytes(bytes): [-35, 2, 55, 25, -108, -16, 93, 31, -22, -30]
     * nextBytes(bytes): [-57, -41, 18, 78, 18, -76, -87, 58, 20, 48]
     * nextBytes(bytes): [32, 12, 16, -3, 46, 62, 6, -54, -23, 125]
     * nextBytes(bytes): [13, -110, 62, -33, -81, 80, 101, 2, 11, -34]
     * nextBytes(bytes): [56, -126, -33, 43, 15, 59, -103, 105, 27, 127]
     * nextBytes(bytes): [-79, -46, 87, -124, 30, -4, -20, -60, -128, 22]
     * 结论:
     */
    @Test
    public void nextBytes() {
        byte[] bytes = new byte[10];

        while (true) {
            random.nextBytes(bytes);
            TestHelper.println("nextBytes(bytes)", Arrays.toString(bytes));
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * author: 2025513
     *
     * 5.测试public void nextLong()
     * 等概率随机返回一个long值
     *
     * 测试思路:
     *
     * 结果:
     * nextLong: -1837788422828352629
     * nextLong: -1396204434210483751
     * nextLong: 8100653424304158238
     * nextLong: 5731771808964690074
     * nextLong: 5972330431345221477
     * nextLong: 4717489013583785150
     * nextLong: -8510306988388364729
     * nextLong: 2775470555901178560
     * nextLong: -3628753738020293883
     * nextLong: -2732424870831121311
     * nextLong: 8048318875962748266
     * nextLong: -2578239998586379261
     * nextLong: -1306173210171198912
     * nextLong: -878319410846344905
     * nextLong: 189134342886560774
     * nextLong: -7691524078467892588
     * nextLong: 605148069062550239
     * nextLong: 954739694308989130
     * nextLong: 7542251912955418572
     * nextLong: -2759490252794816778
     * nextLong: -2586667932324184291
     * nextLong: -3514779935467643849
     * nextLong: -3439475617366141404
     * nextLong: 6418272381796403840
     * nextLong: -827585664423161812
     * nextLong: -2059858820779487569
     * nextLong: -2646113770632218967
     * nextLong: 955998600554290654
     * nextLong: -6086023583949023056
     * nextLong: -5198082299313556487
     * nextLong: -600418839583834712
     * nextLong: 3306728733912016785
     * nextLong: 2273720811009216322
     * nextLong: 1502016566933202670
     * nextLong: -2575907631457554778
     * nextLong: 8035209984232927850
     * nextLong: 2832666068148381934
     * nextLong: -2817370214507223044
     * nextLong: 1733393504805371607
     * nextLong: 2076636172599063571
     * nextLong: 5284513651854816181
     * nextLong: 2666860526054210468
     * nextLong: 2530221263148226471
     * nextLong: -5637248358717713530
     * nextLong: 56346757989286156
     * nextLong: -4527130355082012419
     * nextLong: -6356303662485804747
     * nextLong: -4388438588950020205
     * nextLong: 2731244786900017243
     * nextLong: -8652912155564414930
     * nextLong: -3753141525427450669
     * nextLong: 2801787785566098783
     * nextLong: 2640507793332070784
     * nextLong: 3852493182940975351
     * nextLong: 1826648177181521800
     * nextLong: -8866124861030656288
     * nextLong: -4036306290131871927
     * nextLong: 979604610672630057
     * nextLong: 5025769804909051022
     * nextLong: -5736323811834400414
     *
     * 结论:
     */
    @Test
    public void nextLong() {
        while (true) {
            TestHelper.println("nextLong()", random.nextLong());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * author: 2025513
     *
     * 6.public float nextFloat()测试
     * 【作用】
     * 等概率随机返回[0f,1.0f)之间任意一个float值。
     *
     * 【测试结果】
     *
     * 【结论】
     *
     * 【优点】
     * 【缺点】
     */
    @Test
    public void nextFloat(){
        while(true){
            TestHelper.println("nextFloat()",random.nextFloat());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * author: 2025513 2020年11月17日 11:18:29
     *
     * 7.public double nextGaussian()测试
     * 【作用】
     * nextGaussian的一般约定是，从均值为0.0，标准差为1.0的通常的正态分布(近似)中选择一个双值，伪生成并返回。
     * 
     * 【测试结果】
     * nextGaussian(): -0.6946042946801182
     * nextGaussian(): -0.8217943700390192
     * nextGaussian(): -2.0593958124665184
     * nextGaussian(): 1.3136669981886722
     * nextGaussian(): 0.8766203665639916
     * nextGaussian(): 0.3590565508784329
     * nextGaussian(): 1.3023186176863544
     * nextGaussian(): 0.8564258278313115
     * nextGaussian(): 0.39946079742678137
     * nextGaussian(): -1.1137335582287438
     * nextGaussian(): 0.7954231515617425
     * nextGaussian(): -0.15018926229158114
     * nextGaussian(): 1.304710398478111
     * nextGaussian(): -1.3966438325227752
     * nextGaussian(): 1.625812902337055
     * nextGaussian(): 0.6881400654373279
     * nextGaussian(): 0.7128144276478657
     * nextGaussian(): -2.008780621398987
     * nextGaussian(): 0.7638460114048424
     * nextGaussian(): 1.958534186316467
     * nextGaussian(): 0.3873104915059979
     * nextGaussian(): -0.397904993166726
     * nextGaussian(): -1.113733490442798
     * nextGaussian(): 1.4702983142505532
     * nextGaussian(): -0.22825466788323162
     * nextGaussian(): 0.6968153725723176
     * nextGaussian(): -0.12121795703778529
     * nextGaussian(): -1.666631184227615
     * nextGaussian(): -0.44371306373382113
     * nextGaussian(): 0.452626934327078
     * nextGaussian(): 0.10202690207900594
     * nextGaussian(): 0.12098792492828898
     * nextGaussian(): -0.2424534013181722
     * nextGaussian(): 1.0786089248632857
     * nextGaussian(): -0.7814967291739805
     * nextGaussian(): 0.4959413764107101
     * nextGaussian(): 0.08573279347918447
     * nextGaussian(): 0.975493161555118
     * nextGaussian(): -0.2366864438905919
     * nextGaussian(): -0.9496802339881146
     * nextGaussian(): -0.2819791288730273
     * nextGaussian(): 0.6382292459428367
     * nextGaussian(): -2.125334330033306
     * nextGaussian(): -1.8075754033006912
     * nextGaussian(): 1.4799260419502434
     * nextGaussian(): 0.6866265057931269
     * nextGaussian(): 0.6578484404720644
     * nextGaussian(): 0.22113701205997993
     * nextGaussian(): 1.0842408533025072
     * nextGaussian(): -0.5955171105023341
     * nextGaussian(): -1.6939635697909816
     * nextGaussian(): -1.0309610510981073
     * nextGaussian(): -0.01117374157334461
     * nextGaussian(): -0.7143900016810929
     * nextGaussian(): -1.0997384590372725
     * nextGaussian(): 1.6556942703991566
     * nextGaussian(): 0.9222685530026841
     * nextGaussian(): 0.35074509778631574
     * nextGaussian(): -0.16405785222051972
     * nextGaussian(): 1.498910717093723
     * nextGaussian(): -0.7777338159654361
     * nextGaussian(): -1.216724293060237
     * nextGaussian(): 0.2765864115747149
     * nextGaussian(): -0.37833944765506733
     * nextGaussian(): 0.2905510664311277
     * nextGaussian(): -0.9083647561867544
     * nextGaussian(): 3.056805184943395
     * nextGaussian(): 1.355965014026508
     * nextGaussian(): -0.5121048240019201
     * nextGaussian(): -1.5931355278990005
     * nextGaussian(): -1.4837630169725284
     * nextGaussian(): -0.2736413320109082
     * nextGaussian(): 1.8095003088563268
     * nextGaussian(): -0.2717218382175963
     * nextGaussian(): -0.3195448515148606
     * nextGaussian(): 0.6177698892544305
     * nextGaussian(): -0.0661898381154745
     * nextGaussian(): -0.21281925390160908
     * nextGaussian(): -0.04877534828967926
     * nextGaussian(): -0.8240528212742381
     * nextGaussian(): -0.6126028903452977
     * nextGaussian(): -0.8523671038899346
     * nextGaussian(): 1.0857135457302227
     * nextGaussian(): -0.24104490629594416
     * nextGaussian(): 0.45082658223148886
     * nextGaussian(): 0.9007114759527705
     * nextGaussian(): 0.2281913904142415
     * nextGaussian(): -2.5828111687603594
     * nextGaussian(): 0.2373095603625908
     * nextGaussian(): -1.3668366879921126
     * nextGaussian(): 0.9716647934947874
     * nextGaussian(): -0.18434982150884593
     * nextGaussian(): -0.14778237047907414
     * nextGaussian(): 0.7576162852996317
     * nextGaussian(): 0.09488423350003382
     *
     * 【结论】
     * 
     * 【优点】
     * 【缺点】
     */
    @Test
    public void nextGaussian(){
        while(true){
            TestHelper.println("nextGaussian()",random.nextGaussian());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
