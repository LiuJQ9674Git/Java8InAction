#! /bin/bash -eu

# Save all docker images with repo+tag in the current directory as .tar files.
# It was not tested with images with multiple tags.
# Images can be loaded from bash shell e.g.:
# for img in *.tar ; do docker load -i $img ; done
# Written by Attila Vangel at 2018-03-31

# skip header line
docker images | tail --lines=+2 | while read LINE ; do
  REPO=`echo ${LINE} | awk '{print $1}'`
  TAG=`echo ${LINE} | awk '{print $2}'`
  ID=`echo ${LINE} | awk '{print $3}'`
  # Replace '/' characters in REPO with '_'
  FILENAME=${REPO//\//_}__${TAG}__${ID}.tar
  echo "Saving docker image ${REPO}:${TAG} as ${FILENAME} ..."
  docker save ${REPO}:${TAG} > ${FILENAME}
done


docker images | grep private |while read LINE ; do
  REPO=`echo ${LINE} | awk '{print $1}'`
  TAG=`echo ${LINE} | awk '{print $2}'`
  ID=`echo ${LINE} | awk '{print $3}'`
  # Replace '/' characters in REPO with '_'
  FILENAME=${REPO//\//_}__${TAG}__${ID}.tar
  echo "Saving docker image ${REPO}:${TAG} as ${FILENAME} ..."
done