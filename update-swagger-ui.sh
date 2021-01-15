#!/usr/bin/env sh

rm -rf tmp
git clone https://github.com/swagger-api/swagger-ui.git --no-checkout tmp --depth=1
cd tmp || exit 1
git config core.sparseCheckout true
git sparse-checkout init --cone # to fetch only root files
git sparse-checkout set dist # etc, to list sub-folders to checkout
git read-tree -mu HEAD
cd ..
mkdir -p src/main/resources/static/swagger-ui
mv tmp/dist/*.png src/main/resources/static/swagger-ui/ || exit 1
mv tmp/dist/*.css src/main/resources/static/swagger-ui/ || exit 1
mv tmp/dist/*.html src/main/resources/static/swagger-ui/ || exit 1
mv tmp/dist/swagger-ui-bundle.* src/main/resources/static/swagger-ui/ || exit 1
mv tmp/dist/swagger-ui-standalone-preset.* src/main/resources/static/swagger-ui/ || exit 1
rm -rf tmp
