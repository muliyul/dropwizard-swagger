git clone https://github.com/swagger-api/swagger-ui --no-checkout tmp --depth=1
git config core.sparseCheckout true
git sparse-checkout init --cone # to fetch only root files
git sparse-checkout set dist # etc, to list sub-folders to checkout
git read-tree -mu HEAD
mkdir -p src/main/resources/static/swagger-ui
mkdir tmp
cd tmp || exit 1
mv tmp/dist/favicon-16x16.png src/main/resources/static/swagger-ui || exit 1
mv tmp/dist/favicon-32x32.png src/main/resources/static/swagger-ui || exit 1
mv tmp/dist/swagger-ui.css src/main/resources/static/swagger-ui || exit 1
mv tmp/dist/index.html src/main/resources/static/swagger-ui || exit 1
mv tmp/dist/oauth2-redirect.html src/main/resources/static/swagger-ui || exit 1
mv tmp/dist/swager-ui-bundle.js src/main/resources/static/swagger-ui || exit 1
mv tmp/dist/swager-ui-standalone-preset.js src/main/resources/static/swagger-ui || exit 1
rm -rf tmp
