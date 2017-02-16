

/* 
 * Author: Ryan William Heinrich
 */

#include <stdio.h>
#include <stdlib.h>


#define SHELLY_RL_BUFSIZE 1024 //readline buffer size
char *Mash_read_line(void){
    int bufSize = SHELLY_RL_BUFSIZE;
    int position = 0;
    char  *buffer = malloc(sizeof(char) * bufSize);// memory allocate.
    int c;
    
    if(!buffer){
        fprintf(stderr, "Mash: allocation error\n");
        exit(EXIT_FAILURE);
    }
    
    while(1){
        //Read a character
        c = getchar();
        
        //EOF:end of file must be a library term.
        
        //if we hit EOF, replace it with a null character and return.
        if(c == EOF || c == '\n'){//EndOfFile or newLine char
            buffer[position] =  '\0';//null terminator. signals end of a string.
            return buffer;
        }else{
            buffer[position] = c;
        }
        position++;
        
        //if we have exceeded the buffer, reallocate.
        if(position >= bufSize){
            bufSize += SHELLY_RL_BUFSIZE;
            buffer = realloc(buffer, bufSize);//changes the size(increases buffersize)
            if(!buffer){
                fprintf(stderr, "Mash: allocation error\n");
                exit(EXIT_FAILURE);
            }
        }
    }
    
}
