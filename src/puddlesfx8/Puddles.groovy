package puddlesfx8

/**
 * @author hakim
 */

class Puddles {
 static calculatesPuddles(int [] bars){
        def puddles =[]
        def (index,highest,height,endOfPuddleFound)=[0,0,bars[0],false]
        
        while (highest<bars.length-1){
            index=highest+1
            endOfPuddleFound=false
            while (index<=bars.length-1 ){
                if (bars[index]>=height && index-highest>1){            
                    puddles << [highest,index,height] 
                    highest=index
                    height=bars[highest]
                    endOfPuddleFound=true
                    break                
                }else if (bars[index]>=height && index-highest==1){
                    highest=index
                    height=bars[highest]
                    endOfPuddleFound=true

                } 
                 else index++    
            }
    
            if (!endOfPuddleFound) {
                if (height-1 > bars[highest+1]){            
                    height--
                }else{
                    highest++; 
                    height=bars[highest]
                }         
            } 
        }
        puddles
    } 	
}