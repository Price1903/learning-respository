## description for CopyDirectory (java)(BFS)
java中实现目录拷贝通常采用递归(DFS)的方式,这种方式虽然简单易懂,但是空间复杂度高,拷贝大型目录时占用过多内存空间。是否有别的方式实现?
### BFS概述
#### 思想
**BFS(Breadth Fist Search)广度优先搜索**是一种连通图的遍历算法(查找算法)。BFS的思路是从一个顶点开始,辐射状遍历周围的区域,逐层遍历,故名广度优先搜索。常用来解决:**迷宫问题(最短路径)**。
#### 实现
BFS常用队列实现,**有几个要操作的对象,通常就要建立几个队列**
* 迷宫问题:1个queue
* 二叉树最大深度:2个queue
* 合并二叉树:3个queue

伪代码大致结构:
```java
public static void bfs(操作对象){
	if(不正常情况,比如null) return 特定对象;
   	Queue queue=new LinkedList();//建立queue
    	queue.offer(顶点);//放入顶点
        while(!queue.isEmpty()){
        	while(当前层结点没添加完){
            		queue.poll();//队列出队
            		if(符合加queue条件)queue.offer(当前结点);
                }
         }        
}

```
### 用BFS算法解决目录拷贝
#### 思路
根目录下可能有子目录和文件,子目录下也可能有子目录和文件......最内层目录内容一定为空或一定全是文件。很明显,**目录有层级结构(类似于树)**,满足BFS实现的条件。

定义拷贝目录方法,两个File形参,分别是源目录路径和目标目录路径。如果源目录不存在则结束方法,目标目录不存在则根据路径新建目录。
新建两个队列,分别盛放源的File和目标的File,把源File(形参)和目标File(形参)分别入队两个队列。
每次取出源的File目录下所有子File,取出目标的File,遍历所有子File,如果是文件,则拷贝到目标的File下,如果是目录,则在目标的File下新建同名目录,同时把本目录和新建的目录分别入队两个队列。
#### 代码实现
```java
/**
 * BFS实现目录拷贝
 */
    public static void main(String[] args) {
        File srcFile =new File("E:\\test");//源目录
        File destFile =new File("F:\\test(copy)");//目标目录
        copyDirectory(srcFile,destFile);
    }
    public static void copyDirectory(File srcFile, File destFile){
        if(!srcFile.exists()) return;
        if(!destFile.exists()) destFile.mkdir();
        Queue<File> queue =new LinkedList<>();//盛放源File
        Queue<File> queue1=new LinkedList<>();//盛放目标File
        queue.offer(srcFile);
        queue1.offer(destFile);
        while(!queue.isEmpty()){
            File[] files=queue.poll().listFiles();//取出源File下的所有File
            File dest=queue1.poll();//取出目标File
            int size=files.length;
            for(int i=0;i<size;i++){//遍历源File下的所有File
                File src=files[i];
                if(src.isFile()){//如果是文件,拷到目标目录下
                    copyFile(src,dest);
                }else{//如果是目录,在源目录下新建同名空目录
                    File newDir=new File(dest,src.getName());
                    newDir.mkdir();//建立同名目录
                    //将源目录和新建目录分别加入相应Queue
                    queue.offer(src);
                    queue1.offer(newDir);
                }
            }
        }
    }

    /**
     * 拷贝文件方法
     * @param src 源目录
     * @param dest 目标目录
     */
    public static void copyFile(File src,File dest){
        FileInputStream in=null;
        FileOutputStream out=null;
        try {
            File newFile= new File(dest,src.getName());
            newFile.createNewFile();
            in =new FileInputStream(src);
            out =new FileOutputStream(newFile);
            byte[] bytes =new byte[1024*1024];
            int readCount=0;
            while((readCount=in.read(bytes))!=-1){
                out.write(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
```
#### 总结
深刻理解各种数据结构和算法,对实际问题进行分析、建模,学以致用。

