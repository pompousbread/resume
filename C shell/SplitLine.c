
/* 
 * File:   SplitLine.c
 * Author: Ryan William Heinrich

 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//fits 8 bytes
#define SHELLY_TOK_BUFSIZE 64 //token buffer size
#define SHELLY_TOK_DELIM " \t\r\n"//for some reason the a doesn't work(look into it)
/*list of escape characters
 * /t:tab
 * /r:Carriage return: <CR> - moves cursor to the front position of the same line.
 * /n:newline
 * /a:audible bell or alert
 */

/*
 * no quoting or backslash escaping in our command line arguments
 * tokenize: to reduce to a set of tokens by parsing.(spliting a string into token strings)
 */
char **Mash_split_line(char *line){
    int bufsize = SHELLY_TOK_BUFSIZE, position = 0;
    char **tokens = malloc(bufsize * sizeof(char*));
    char *token;
    
    if(!tokens){
        fprintf(stderr, "Mash: allocation error/n");
        exit(EXIT_FAILURE);
    }
    
    token = strtok(line, SHELLY_TOK_DELIM);
    while(token != NULL){
        tokens[position] = token;
        position++;
        
        if(position >= bufsize){
            bufsize += SHELLY_TOK_BUFSIZE;
            tokens = realloc(tokens, bufsize * sizeof(char*));
            if(!tokens){
                fprintf(stderr, "Mash: allocation error\n");
                exit(EXIT_FAILURE);
            }
        }
        token = strtok(NULL, SHELLY_TOK_DELIM);
    }
    tokens[position] = NULL;
    return tokens;

}
