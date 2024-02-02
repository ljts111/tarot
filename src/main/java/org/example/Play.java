package org.example;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 玩塔罗牌
 */
@RestController
@RequestMapping("/play")
public class Play {

    @RequestMapping("/playTarot")
    public static String playTarot() {
        // 码牌
        List<String> decks = new ArrayList<>();
        decks.addAll(Arrays.asList(Card.bigArcana));
        decks.addAll(Arrays.asList(Card.smartArcana));

        // 洗牌
        Collections.shuffle(decks);

        // 切牌
        int num = decks.size();
        int incision1 = (int) (Math.random() * ((num - 2) + 1)) + 2;
        int incision2 = (int) (Math.random() * ((num - 1) - (incision1 + 1) + 1) + incision1 + 1);
        System.out.println(incision1);
        System.out.println(incision2);
        List<String> decks1 = decks.subList(incision1, incision2);
        List<String> decks2 = decks.subList(incision2, num);
        List<String> decks3 = decks.subList(0, incision1);
        List<String> newDecks = new ArrayList<>();
        newDecks.addAll(decks1);
        newDecks.addAll(decks2);
        newDecks.addAll(decks3);

        // 时间阵
        List<String> randoms = new ArrayList<>();
        while (randoms.size() != 3) {
            String random = newDecks.get((int) (Math.random() * (newDecks.size() - 1 + 1)));
            if (ArrayUtils.contains(Card.bigArcana, random)) {
                if ((int) (Math.random() * (1 + 1)) == 0) {
                    random += "正位";
                } else {
                    random += "逆位";
                }
            }
            if (!randoms.contains(random)) {
                randoms.add(random);
            }
        }

        StringBuilder output = new StringBuilder();
        System.out.println("选出的卡牌:" + randoms.toString());
        for (String i : randoms) {
            System.out.println(i + ":" + Paraphrases.getValue(i));
            output.append(i).append(":").append(Paraphrases.getValue(i)).append("<br/>");
        }
        return output.toString();
    }

    public static void main(String[] args) {
        HashMap<String,Integer> phone = new HashMap<String,Integer>();
        phone.put("Apple", 7299);
        phone.put("SAMSUNG", 6000);
        phone.put("Meizu", 2698);
        phone.put("Xiaomi", 2400);
        //key-sort
        Set set = phone.keySet();
        Object[] arr = set.toArray();
        Arrays.sort(arr);
        for (Object key : arr) {
            System.out.println(key + ": " + phone.get(key));
        }
        System.out.println();
        //value-sort
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(phone.entrySet());
        //lambda.sort()
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        //list.sort()
        list.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        //collections.sort()
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        //for
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getKey() + ": " + list.get(i).getValue());
        }
        System.out.println();
        //for-each
        for (Map.Entry<String, Integer> mapping : list) {
            System.out.println(mapping.getKey() + ": " + mapping.getValue());
        }
    }
}

