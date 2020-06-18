rmdir ./docs/.vuepress/dist --ignore-fail-on-non-empty

yarn docs:build

cd ./docs/.vuepress/dist

git init

git add -A

git commit -m 'deploy'

git remote add origin  https://github.com/javayuga/jwt-spring-boot-template.git

git push origin --delete gh-pages

git push -f https://github.com/javayuga/jwt-spring-boot-template.git master:gh-pages
