import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Trie {
    static class TrieNode {
        Map<Character, TrieNode> children = new TreeMap<Character, TrieNode>();
        boolean leaf;
    }

    static TrieNode root = new TrieNode();

    public static void put(String s) {
        TrieNode v = root;
        for (char ch : s.toLowerCase().toCharArray()) {
            if (!v.children.containsKey(ch)) {
                v.children.put(ch, new TrieNode());
            }
            v = v.children.get(ch);
        }
        v.leaf = true;
    }

    public boolean find(String s) {
        TrieNode v = root;
        for (char ch : s.toLowerCase().toCharArray()) {
            if (!v.children.containsKey(ch)) {
                return false;
            } else {
                v = v.children.get(ch);
            }
        }
        return true;
    }

    public static void printSorted(TrieNode node,String in) {
        if (node.leaf) {
            System.out.print(in);
            System.out.println();
        }
        for (Character ch : node.children.keySet()) {
            printSorted(node.children.get(ch),in+ch);
        }
    }

    public TrieNode showHint(String s){
        TrieNode v = root;
        int count=1;
        TrieNode n =new TrieNode();
        for (char ch : s.toLowerCase().toCharArray()) {
            if (!v.children.containsKey(ch)) {
                return n;
            } else {
                v = v.children.get(ch);
            }
        }
        return v;
    }
    public static void input(String input) {
        try {
            Scanner in = new Scanner(new File(input));
            while (in.hasNextLine())
                put(in.nextLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Usage example
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.put("hello");
        trie.input("/Users/user/IdeaProjects/Tries/src/inp.txt");
        Scanner in = new Scanner(System.in);
        System.out.print("Введите слово для поиска: ");
        String inputText = in.next();
        if(!trie.find(inputText))
            System.out.print("Такого слова нет в словаре, возможно допущена ошибка при вводе");
        else  printSorted(trie.showHint(inputText),inputText);
    }
}