class RnaTranscription {

    String transcribe(String dnaStrand) {
        
        StringBuilder rnaStrand = new StringBuilder();
        
        for(int i = 0; i < dnaStrand.length(); i++){
            char current = dnaStrand.charAt(i);
            if(current == 'A'){
                rnaStrand.append('U');
            }
            else if( current == 'T'){
                rnaStrand.append('A');
            }
            else if( current == 'G'){
                rnaStrand.append('C');
            }
            else if( current == 'C'){
                rnaStrand.append('G');
            }
        }
        return rnaStrand.toString();
    }

}
