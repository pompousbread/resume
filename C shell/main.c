/* 
 * File:   main.c
 * Author: Ryan William Heinrich
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>
#include <time.h>

/*
 *
 * 
 * 
 */
char *Mash_read_line(void);
char **Mash_split_line(char *line);
extern int (*builtin_funct[])(char**);
extern char *builtin_str[];
int Mash_num_builtins();


int Mash_launch(char **args, char *line){
    pid_t pid, wpid;
    int status;//waitpid() stores info in here.
    //printf("launch executed!\n");
    
    
    
    int numberOfPipes = 0;
    char tempArgHolder[256]= "";
    char fullLine[256] = "";//init it.
    
    int lastElementIndex = 0;
    for(int index = 0; index < (sizeof( args )*2); index++){   
        //cdprintf("arg[%d]: %s\n",index, args[index]);
        if(args[index] != NULL){
                lastElementIndex++;
        }if(args[index] == NULL){
            break;
        }if(strcmp(args[index], ">") == 0 || strcmp(args[index], "<") == 0 || strcmp(args[index], "|") == 0){
                numberOfPipes++;
        }
    }
    
    //printf("last|%d|\n", lastElementIndex);
    
    char **argumentHolder1[8];
    char *pipeArgsHolder[8];
    char *commandHolder[8];//has full command and not separated args.
    
    
    //this formates the args to properly be executed.
    int temp = 0;
    for(int index = 0; index < (sizeof( args ) + 40); index++){//start on 1. 
        
        char* argSplit  = malloc(sizeof(char) * 25 + 1);// args cant be more than 25 chars
        
        if(args[index] == NULL){
            //printf("break it\n");
            break;
        }
        
        if(strcmp(args[index], ">") == 0 || strcmp(args[index], "<") == 0 || strcmp(args[index], "|") == 0){//got it use a delimiter to parse anything string with " <, >, or |"
            pipeArgsHolder[temp] = malloc(sizeof(char) * 10 + 1);
            int idxToDel = strlen(fullLine) -1;
            memmove(&fullLine[idxToDel], &fullLine[idxToDel + 1], strlen(fullLine) - idxToDel);
            strcpy(argSplit, "");
                        
            //printf("before|%s|: %d\n", fullLine, index);
            
            //I need to allocated space. these variables are all pointers redirecting.
            strcpy(argSplit, fullLine);//-1 for >, -1 for " "(white space)            
            strcpy(tempArgHolder, fullLine);//-1 for >, -1 for " "(white space)                      
            memset(fullLine,0,sizeof(fullLine));
            
            //printf("1|%s|: %d\n", fullLine, index);  
            commandHolder[temp] = tempArgHolder;
                        
            //printf("3split|%s|\n", argSplit);
            //printf("%d : %s\n", temp ,args[index]);

            argumentHolder1[temp] = Mash_split_line(argSplit);
            pipeArgsHolder[temp] = args[index];
            //strcpy(pipeArgsHolder[temp], args[index]);
            temp++;
        }else{
            strcat(fullLine, args[index]);
            if(index != (lastElementIndex -1)){//not last argument.
                strcat(fullLine, " ");
            }
            //printf("else test:|%s|\n", fullLine);
        }
    }
    //printf("1|%s|\n", fullLine);  
    argumentHolder1[temp] = Mash_split_line(fullLine);
    //printf("2|%s|\n", argumentHolder1[temp][0]);
    
    
    //Test cases:
    //sort list.txt > sorted.txt (done)
    //ls -l > output.txt (done)
    //ls -l | grep main (done)
    //ls -l | wc (done)
    //ls -l | grep main | wc(done)
    //sort < alice.txt > sorted.txt (done)
    //ls -l | grep band > output.txt(done)
    //wc -l < list.txt(done)
    // sort < ls -l | grep band(done)
    
    
    FILE *fp;
    int in;
    char c[1000];
    char buffer[100000];//buffer for temp storage.
    int argsIter = 0;
    int n;      
    
    int fdPipes[numberOfPipes][2];//array of pipes.
    int i = 0;
    while(i < numberOfPipes){//creates as many pipes as needed.
            if(pipe(fdPipes[i]) < 0){//creates pipe.
                perror("pipe");
                exit(-1);
            }
            i++;
    }
    
    //printf("\n\n\n");
    for(int i = 0; i < numberOfPipes+1; i++){
           
        //printf("prcs:%d pipes: %d\n", i, numberOfPipes);
        //printf("!:%s \n", pipeArgsHolder[0]);//can create segmentation fault.
        pid = fork();
        if(pid == 0){
            
             
            
            //in child process
            argsIter = i - 1;
            if(numberOfPipes == 0){
                in = execvp(argumentHolder1[i][0] , argumentHolder1[i]);
            }
            
            
            //printf("args: | %s | %d\n", pipeArgsHolder[argsIter], i);
            if(i == 0 && (strcmp(pipeArgsHolder[0], "|") == 0 || strcmp(pipeArgsHolder[0], ">") == 0)  ){
                    //printf("execute start\n\n");
                    
                    dup2(fdPipes[i][1] , 1);//prces1 write to pipe
                    in = execvp(argumentHolder1[i][0] , &argumentHolder1[i][0]);
            } 
            
            else if(strcmp(pipeArgsHolder[argsIter], "|") == 0 && i != 0){//1 pipe case
                
                //printf("piping\n");
                //printf("execute %d\n\n", i);
                if(i < numberOfPipes){//next pipe is not empty
                            //printf("pipe %s\n",pipeArgsHolder[argsIter+1]);
                            dup2( fdPipes[i-1][0], 0);//prces 1 read from pipe.
                            dup2( fdPipes[i][1], 1);//prces2 write to pipe
                            argsIter++;
                            in = execvp(argumentHolder1[i][0] , argumentHolder1[i]);
                }else{
                    dup2( fdPipes[i-1][0], 0);//read last process
                    argsIter++;
                    //printf("last\n");
                    
                    execvp(argumentHolder1[i][0] , argumentHolder1[i]);
                }
            }
            
            //printf("past pipes\n");          
            if(strcmp(pipeArgsHolder[argsIter], ">") == 0){                  
                    if(strcmp(pipeArgsHolder[argsIter-1], ">") == 0){
                        int in = open(argumentHolder1[i-1][0],O_RDONLY|O_CREAT,0666); 
                        int out = open(argumentHolder1[i][0],O_WRONLY|O_CREAT,0666); // Should also be symbolic values for access rights
                        dup2(out,STDOUT_FILENO);
                        close(out);
                        argsIter++;
                    
                        if ((n = read(in, buffer, 100000)) >= 0) {
                                buffer[n] = 0;	/* terminate the string */
                                printf("%s", buffer);
                        }	
                        else{
                                perror("read");
                        }
                    }
                    else{
                        dup2( fdPipes[i-1][0], 0);//prces 1 read from pipe.
                        int out = open(argumentHolder1[i][0],O_WRONLY|O_CREAT,0666); // Should also be symbolic values for access rights
                        dup2(out,STDOUT_FILENO);
                        close(out);
                        argsIter++;
                    
                        if ((n = read(fdPipes[i-1][0], buffer, 100000)) >= 0) {
                                buffer[n] = 0;	/* terminate the string */
                                printf("%s", buffer);
                        }	
                        else{
                                perror("read");
                        }
                    }
            }
            
            if(i == 0 && strcmp(pipeArgsHolder[0], "<") == 0 && strcmp(pipeArgsHolder[1], ">") == 0){
                //wc -l < list.txt > output.txt
                int in = open(argumentHolder1[1][0],O_RDONLY);
                dup2(in,STDIN_FILENO);
                close(in);
                int out = open(argumentHolder1[2][0],O_WRONLY|O_CREAT,0666); // Should also be symbolic values for access rights
                dup2(out,STDOUT_FILENO);
                close(out);
                execvp(argumentHolder1[0][0], &argumentHolder1[0][0]);
                
            }
            
            else if(strcmp(pipeArgsHolder[i], "<") == 0){
                //wc -l < list.txt
                printf("line: %s\n", line);
                //Close stdin, duplicate the input side of pipe to stdin

                FILE *fout = fopen(argumentHolder1[1][0], "r");//reads file
                fp = popen(line, "w");//param 2: either r ,or w.
                int c;
                if (fp) {
                    while ((c = getc(fp)) != EOF){
                            fprintf(fout, "%c", c);
                    }
                    fclose(fp);
                }
                fclose(fout);
                goto loopExit;
                
            }
           
            if(in == -1){//-1 = execution fails
                     perror("Mash");
            }
            exit(EXIT_FAILURE);
       }else if(pid < 0){//Error forking
            //close(fd1[1])l
            perror("Mash");
       }else{
            //in parent process
           if(i < numberOfPipes){//it will try to close a pipe that is not there.
                        close(fdPipes[i][1]);
            }
            
       }
        
    }     
    do{
                //wait insures that the child goes first.
                //wait also insures that the child terminates properly preventing it from becoming a zombie.

                //pid_t waitpid(pid_t pid, int *status, int options);
                wpid = waitpid(pid, &status, WUNTRACED);//suspends execution of pid(calling process) until a child is terminated.
                //WUNTRACED:return if no child has been exited or if  child has stopped.
   }while(!WIFEXITED(status) && !WIFSIGNALED(status));
   
   loopExit:
   return 1;
    
}

int Mash_execute(char **args, char *line){
        int i;
        if (args[0] == NULL) {
                // An empty command was entered.
                return 1;
        }

        for (i = 0; i < Mash_num_builtins(); i++) {
                if (strcmp(args[0], builtin_str[i]) == 0) {
                    //printf("working: %s\n", builtin_str[i]);
                        return (*builtin_funct[i])(args);
                        //never reaches launch.
                }
        }     

        return Mash_launch(args, line);
}

void Shelly_loop(void){
    char *line;//ptr
    char **args;//ptr to a pointer.(possibly a 2d array)
    int status;
    char buf[1024];
    
    char cwd[1024];//holds the current directory.
    
    
    do{
        if (getcwd(cwd, sizeof(cwd)) != NULL){
                fprintf(stdout, "%s> ", cwd);//print current path.
        }else
                perror("getcwd() error");
        
        line = Mash_read_line();//in ReadLine
        strcpy(buf, line);
        args = Mash_split_line(line);
        status = Mash_execute( args, buf);
        
        free(line);
        free(args);
    }while(status);//executes once before checking.
}

int main(int argc, char** argv) {
    //load config files, if any.

    //run command loop.
    Shelly_loop();
    
    return (EXIT_SUCCESS);
    //Perform any shutdown/cleanup
}

