
/* 
 * File:   ShellBuiltins.c
 * Author: Ryan William Heinrich
 */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

/*
 function declarations for builtin shell commands:
 */
int Mash_cd( char **args);
int Mash_help(char **args);
int Mash_exit(char **args);

/*
 List of builtin commands, followed by their corresponding functions.
 */
char *builtin_str[] = {
    "cd",
    "help",
    "exit"
};

int (*builtin_funct[])( char**) = {
    &Mash_cd,
    &Mash_help,
    &Mash_exit
};

int Mash_num_builtins(){
    return sizeof(builtin_str) / sizeof(char *);
}

/*
  Builtin function implementations.
 * cd only works with one string directories. anything more it will not recognize.
*/
int Mash_cd(char **args)
{
    
    
  //okay this reads args[0] but keeps reading args[1] as null.
  if(args[1] == NULL) {//return to home directory.
      chdir(getenv("HOME"));
    //fprintf(stderr, "Shelly: expected argument to \"cd\"\n");
  }else {
        //printf("\nt1: %s\n", args[1]);
        //printf("t2: %d\n", counter);//this works im almost there.

        int counter= 1;
        char test[100];
        while(args[counter] != NULL){
                counter++;
        }
        int testNum =1;
        while(args[testNum] != NULL){
                strcat(test, args[testNum]);
                testNum++;
                if(testNum != counter ){
                        strcat(test, " ");
                }
               
        }
        //printf("\nt1:%s I\n", test);
        chdir(test);
  }
  return 1;
}

int Mash_help(char **args)
{
  int i;
  printf("Ryan Heinrich's shell\n");
  printf("Type program names and arguments, and hit enter.\n");
  printf("The following are built in:\n");

  for (i = 0; i < Mash_num_builtins(); i++) {
    printf("  %s\n", builtin_str[i]);
  }

  printf("Use the man command for information on other programs.\n");
  return 1;
}

int Mash_exit(char **args)
{
  return 0;
}