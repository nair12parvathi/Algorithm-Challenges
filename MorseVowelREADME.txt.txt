MorseVowel:

Given is a sequence of n symbols, each of which is either a dot (.) or a dash (-). This can represent a sequence of letters in Morse code. However, since the separation between letters is not given, it can represent a number of different sequences. For example, . - - could represent ETT, AT, EM, or W. Give an O(n) algorithm that computes the number of possible letter sequences containing only vowels (A,E,I,O,U) that can be derived from the given input sequence of dots and dashes. As your argument of correctness, state all three parts of the heart of your solution (see below).

Input specification: the first line contains n, indicating the length of the string of symbols to follow. The second line contains a single string of length n, comprised of only '.' and '-' characters. 

You may assume that the Morse Code string is no longer than 100 characters. 

Output specification: the output contains a single line, containing the number of possible decodings for this Morse Code string that comprise only vowels, followed by the end-of-line symbol "\n". 

Sample Input and Output:

1.

6
...-..

>>6

2.

13
..---.-..-..-

>>8

3.

20
...-.-.---..--......

>>0

4.

100
.-..---......-..---.----....---.-....-.-..-.-...---...-.---.-...-....---.------.........---..-....-.

>>193050000
