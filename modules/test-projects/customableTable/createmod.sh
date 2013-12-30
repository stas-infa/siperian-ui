#!/bin/sh
echo >dependencies.ent
for i in `ls  ../richfaces/ui`;do
if [ -d "../richfaces/ui/$i" -a $i != "assembly" ];then
echo '<dependency>' >>dependencies.ent
echo '                        <groupId>org.richfaces.ui</groupId>' >>dependencies.ent
echo '                        <artifactId>'$i'</artifactId>' >>dependencies.ent
echo '                        <version>${richfaces}</version>' >>dependencies.ent
echo '                        <exclusions>' >>dependencies.ent
echo '                                <exclusion>' >>dependencies.ent
echo '                                        <groupId>javax.faces</groupId>' >>dependencies.ent
echo '                                        <artifactId>jsf-api</artifactId>' >>dependencies.ent
echo '                                </exclusion>' >>dependencies.ent
echo '                                <exclusion>' >>dependencies.ent
echo '                                        <groupId>javax.faces</groupId>' >>dependencies.ent
echo '                                        <artifactId>jsf-impl</artifactId>' >>dependencies.ent
echo '                                </exclusion>' >>dependencies.ent
echo '                        </exclusions>' >>dependencies.ent
echo '</dependency>' >>dependencies.ent
fi
done