package jp.co.internous.wings.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import jp.co.internous.wings.model.domain.TblCart;
import jp.co.internous.wings.model.domain.dto.CartDto;

@Mapper
public interface TblCartMapper {

	List<CartDto> findCart(int userId);

	@Select("SELECT * FROM tbl_cart WHERE user_id = #{userId} ")
	List<TblCart> findByUserId(@Param("userId") int userId);

	List<CartDto> findTblCart(@Param("userId") int userId);

	// ユーザーに紐づくカート情報に、追加する商品IDと一致するデータが存在しない場合
	@Insert("INSERT INTO tbl_cart (user_id, product_id, product_count ) "
			+ "VALUES (#{userId}, #{productId}, #{productCount} )")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	void insert(TblCart tblCart);

	@Select("SELECT count(product_id) FROM tbl_cart WHERE user_id = #{userId} AND product_id = #{productId}")
	int findByProductIdAndUserId(@Param("userId") int userId, @Param("productId") int productId);

	// ユーザーに紐づくカート情報に、追加する商品IDと一致するデータが存在する場合
	@Update("UPDATE tbl_cart SET product_count = product_count + #{productCount}, updated_at = now() WHERE user_id = #{userId} AND product_id = #{productId}")
	int updateProductCountByProductIdAndUserId(@Param("productCount") int productCount,
			@Param("productId") int productId, @Param("userId") int userId);

	@Delete("DELETE FROM tbl_cart WHERE id = #{id}")
	void deleteById(int id);

	// 下記の引数を受け取り、tempUserId(仮ユーザーID)と一致するuser_idを検索し、そのuser_idをuserIdで上書きするメソッド
	@Update("UPDATE tbl_cart SET user_id = #{userId}, updated_at = now() WHERE user_id = #{tempUserId}")
	void updateUserId(@Param("userId") int userId, @Param("tempUserId") int tempUserId);

	@Delete("DELETE FROM tbl_cart WHERE user_id = #{userId}")
	int deleteByUserId(int userId);

}
