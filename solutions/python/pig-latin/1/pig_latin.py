def translate_word(word):

      vowels = ["a", "e", "i", "o", "u"]
      word = word.lower()
      letters = list(word)

      if(word[0] in vowels) or (word.startswith("xr")) or (word.startswith("yt")):
            return word + "ay"
      
      constonant = 0
       
      for letter in word:

            if (word.startswith("qu")):
                  word = word[2:] + "qu" 
                  return word + "ay"
            
            elif (letter == "y" and letter != letters[0]):
                  return word + "ay"

            elif(letter not in vowels):
                  constonant = constonant + 1
                  word = word[1:] + word[0]
                  print("consonant no", constonant, letter,"and the new word is" , word)
            
            else:
                  return word + "ay"


      return word + "ay"

proceed = True

def translate(phrase):
    words = phrase.strip().split()
    translated_words = []
    for word in words:
        translated_words.append(translate_word(word))
    return " ".join(translated_words)