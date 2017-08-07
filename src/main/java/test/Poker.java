package test;

/**
 * 扑克类（一副扑克）
 *
 * @author joels
 * @create 2017-07-03 11:39
 **/
public class Poker {
    private static String[] suites = {"黑桃", "红桃", "草花", "方块"};
    private static int[] faces = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

    private Card[] cards;

    /**
     * 构造器
     */
    public Poker() {
        cards = new Card[52];
        for (int i = 0; i < suites.length; i++) {
            for (int j = 0; j < faces.length; j++) {
                cards[i * 13 + j] = new Card(suites[i], faces[j]);
            }
        }
    }

    /**
     * 得到跑得快的牌
     *
     * @return 跑得快的牌
     */
    public Card[] paoDeKuai() {
        Card[] paoDeKuaiCards = new Card[48];
        int index = 0;
        for (int i = 0; i < cards.length; i++) {
            //去掉四张2和一张A
            if (cards[i].getFace() == 2 ||
                    (cards[i].getFace() == 1 &&
                            cards[i].getSuite().equals("黑桃"))) {
                continue;
            }
            paoDeKuaiCards[index++] = cards[i];
        }
        paoDeKuaiCards[index++] = new Card("黑桃", 2);
        return paoDeKuaiCards;
    }

    /**
     * 切牌
     *
     * @param sourceCards 原牌堆
     * @param minNum      最少张数限制
     * @return 切牌后的牌堆
     */
    public Card[] cut(Card[] sourceCards, int minNum) {
        //随机切掉多少张牌
        int lenth = sourceCards.length - (int) (Math.random() * (sourceCards.length - minNum));
        Card[] destCards = new Card[lenth];
        System.arraycopy(sourceCards, sourceCards.length - lenth - 1, destCards, 0, lenth);
        return destCards;
    }

    /**
     * 洗牌 （随机乱序）
     */
    public void shuffle(Card[] cards) {
        for (int i = 0, len = cards.length; i < len; i++) {
            int index = (int) (Math.random() * len);
            Card temp = cards[index];
            cards[index] = cards[i];
            cards[i] = temp;
        }
    }

    /**
     * 发牌
     *
     * @param index 发牌的位置
     */
    public Card deal(Card[] cards, int index) {
        return cards[index];
    }

    /**
     * 卡片类（一张扑克）
     * [内部类]
     */
    public class Card {
        private String suite;   // 花色
        private int face;       // 点数

        public Card(String suite, int face) {
            this.suite = suite;
            this.face = face;
        }

        public String getSuite() {
            return suite;
        }

        public int getFace() {
            return face;
        }

        @Override
        public String toString() {
            String faceStr;
            switch (face) {
                case 1:
                    faceStr = "A";
                    break;
                case 11:
                    faceStr = "J";
                    break;
                case 12:
                    faceStr = "Q";
                    break;
                case 13:
                    faceStr = "K";
                    break;
                case 0:
                    faceStr = "";
                    break;
                default:
                    faceStr = String.valueOf(face);
            }
            return suite + faceStr;
        }
    }

    public static void main(String[] args) {
        Poker poker = new Poker();
        poker.shuffle(poker.cards);                // 洗牌
//        Poker.Card c1 = poker.deal(0);  // 发第一张牌
//        // 对于非静态内部类Card
//        // 只有通过其外部类Poker对象才能创建Card对象
//        Poker.Card c2 = poker.new Card("红心", 1);    // 自己创建一张牌
//
//        System.out.println(c1);     // 洗牌后的第一张
//        System.out.println(c2);     // 打印: 红心A

        //得到跑得快的牌
        Poker.Card[] paodekuaiCards = poker.paoDeKuai();
        poker.shuffle(paodekuaiCards);
        //发三方牌
        Poker.Card[][] threePersons = new Poker.Card[3][16];
        for (int i = 0; i < paodekuaiCards.length; i++) {
            threePersons[i % 3][i / 3] = paodekuaiCards[i];
        }
        //打印三个人的牌
        System.out.println("跑得快,三个人的牌:");
        for (int i = 0; i < threePersons.length; i++) {
            for (int j = 0; j < threePersons[i].length; j++) {
                System.out.print(threePersons[i][j] + " ");
            }
            System.out.println();
        }

        //斗牛
        poker.shuffle(poker.cards);
        //发四方牌
        Poker.Card[][] fourPersons = new Poker.Card[4][5];
        //切牌
        Poker.Card[] douniuCards = poker.cut(poker.cards, 20);
        for (int i = 0; i < fourPersons.length * fourPersons[0].length; i++) {
            fourPersons[i % 4][i / 4] = douniuCards[i];
        }
        //打印四个人的牌
        System.out.println("斗牛,四个人的牌:");
        for (int i = 0; i < fourPersons.length; i++) {
            for (int j = 0; j < fourPersons[i].length; j++) {
                System.out.print(fourPersons[i][j] + " ");
            }
            System.out.println();
        }
    }
}
