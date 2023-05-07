form collections import defaultdict

def group_anagram(strings):
    group_anagrams = defaultdict(list)
    
    for word in strings:
        #create a sorted tuple of the letters in the word
        
        sorted_word = tuple(sorted(word))
        #Add the word to the corresponding group_anagram
        anagram_group [sorted_word].append(word)
        
    return list(anagram_group.values())
    
    
strings = ["eat","tea","tan","ate","nat","bat"]
anagram_group = group_anagrams(strings)
print(anagram_groups)