############################################################
# Executa programa standalone
############################################################

############################################################
# adeque o caminho abaixo para o diretorio raiz da aplicacao
############################################################
DIRETORIO_EXECUCAO=/projetos/diretorio-programa-batch
############################################################
# configure aqui o programa a ser executado e opções Java
############################################################
JAVA_CMD=-Xms512m -Xmx1g -XX:PermSize=512m -XX:MaxPermSize=512m
############################################################
# certifique-se que JAVA_HOME esta correto
############################################################
JAVA_HOME=/opt/java6/jre

##########################################################
# Daqui para baixo deve ser igual para todos os programas
##########################################################
DIR_LIB=$DIRETORIO_EXECUCAO/lib;

# Coloca os jar utilizados no classpath
CP=".";
cd $DIR_LIB
for i in *.jar
do
  CP=$CP:lib/$i;
done

cd $DIRETORIO_EXECUCAO

$JAVA_HOME/bin/java -cp $CP $JAVA_CMD